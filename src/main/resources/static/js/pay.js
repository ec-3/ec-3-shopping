$(function () {
    $(".header-all").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));
    function init() {
        let coinList = localStorage.getItem("coinList");
        if (coinList) {
            forCoins(JSON.parse(coinList));
            forNetwork(JSON.parse(localStorage.getItem("networks")));
        } else {
            post(ec3Mapping.coin, JSON.stringify({}), function (response) {
                if (response.code === 0) {
                    let coinObj = response.data;
                    if (coinObj) {
                        localStorage.setItem('coinList', JSON.stringify(coinObj.coins));
                        localStorage.setItem('networks', JSON.stringify(coinObj.chainNetworks));
                        localStorage.setItem('coinNet', JSON.stringify(coinObj.coinNet));
                    }
                    forCoins(coinObj.coins);
                    forNetwork(coinObj.chainNetworks);
                } else {
                    console.log(response.msg);
                }
            }, function (error) {
                console.log(error_msg + error);
            });
        }
        if (!localStorage.getItem("merchant")) {
            post(ec3Mapping.merchant, JSON.stringify({}), function (res) {
                localStorage.setItem("merchant", JSON.stringify(res.data))
            });
        }
    }

    init();

    /***************************************/

    /*************** pay ************************/
    async function paying() {
        let ethereum = window.ethereum;
        if (!ethereum) {
            layer.msg("please install wallet first!");
            return;
        }
        if (await ethereum.request({method: 'eth_accounts'})) {
            let account = await ethereum.request({method: 'eth_requestAccounts'});
            if (account.length > 0) {
                let order = getOrder();
                if (order) {
                    let provider = new ethers.providers.Web3Provider(ethereum);
                    let signer = provider.getSigner();
                    let contract = new ethers.Contract(Goerli_ETH_USDC.address, Goerli_ETH_USDC.abi, signer);
                    let amount = ethers.utils.parseUnits(order.payAmount + '', 6);
                    let result = contract.transfer(getPayAddress(), amount);
                    result.then((res) => {
                        order['status'] = 1;
                        order['chainTx'] = res.hash;
                        order['payTime'] = new Date();
                        order['chainId'] = ethereum.networkVersion;
                        order = JSON.stringify(order);
                        post(ec3Mapping.order_update, order, function (response) {
                            if (response.code === 0) {
                                localStorage.setItem('submitted_order', order);
                                localStorage.removeItem('to_be_paid_order');
                                page('account');
                            } else{
                                console.log(response.msg);
                            }
                        }, function (error) {
                            console.log(error_msg + error);
                        });
                         layer.msg('Payments. . . . Please check your order later');
                        $('#ConnectW').css('display', 'none');

                    }).catch((err) => {
                        layer.msg('Payment canceled or failed');
                        $('#ConnectW').css('display', 'flex');
                    });
                }
            } else {
                layer.msg('Wallet not connected');
                $('#ConnectW').css('display', 'flex');
            }
        }
    }

    function getPayAddress() {
        let wt = $('#select-network').text();
        let payList = JSON.parse(localStorage.getItem("merchant"));
        for (let k in payList) {
            if (wt ===  payList[k].walletType) {
                return payList[k].address;
            }
        }
    }

    function getOrder() {
        let order = localStorage.getItem('to_be_paid_order');
        if (!order) {
            let orderId = localStorage.getItem('pay_orderId');
            return JSON.parse(localStorage.getItem('wait_order'))[orderId];
        }
        return JSON.parse(order);
    }

    $('#ConnectW').click(function () {
        paying();
    });
    /*************** pay ************************/

    $('.select-token').click(function () {
        $('.token-boss').css("display", "inline-block");
    });
    $('.token-close').click(function () {
        $('.token-boss').css("display", "none");
    });
    $('.select-network-base').click(function () {
        $('.network').css("display", "inline-block");
    });
    $('.network-close').click(function () {
        $('.network').css("display", "none");
    });

    $(".token-body-c2").on('click', '.token-item', function () {
        let index = $('.token-item').index(this);
        let icon = $('.token-img').eq(index).attr('src');
        let currency = $('.token-name').eq(index).text();
        let currencyFullName = $('.token-company').eq(index).text();
        $('#select-token-img').attr('src', icon);
        $('#select-token-name').text(currency);
        $('#select-token-full-name').html(currencyFullName);
        $('.token-boss').css("display", "none");

    });
    $(".network-27").on('click', '.network-item', function () {
        let index = $('.network-item').index(this);
        let netId = $(this).attr('netId');
        let network = $('.network-name').eq(index).text();
        let networkName = $('.network-name-info').eq(index).text();
        $('#select-network').text(network);
        $('#select-network-name').html(networkName);
        $('.network').css("display", "none");
        //check();
        paying();
    });


    function forCoins(coinList) {
        let lis = '';
        let index = 0;
        for (let coin of coinList) {
            lis += `<div class="token-item" style="transform: translateY(${index}px);">
                                                <div class="token-info">
                                                    <img src="${coin.icon}" class="token-img"/>
                                                <div class="svg-path">
                    <span class="token-name">${coin.currency}</span>
                    <label class="token-company">${coin.currencyFullName}</label>
                                                    </div>
                                                </div>
                                                <div class="token-amount">
                                                    <small class="token-money">0</small>
                                                    <span class="token-money-all">
                                                    <span dir="ltr" class="token-symbol">
                                                    <span dir="ltr" class="token-symbol">${coin.price}</span>
                                                    <span class="svg-path">USD</span>
                                                    </span></span>
                                                </div>
                                            </div>`;
            index += 72;
        }
        $('.token-body-c2').append(lis);
    }

    function forNetwork(networks) {
        let lis = '';
        for (let network of networks) {
            lis += ` <div class="network-item" netId="${network.id}">
                         <div class="svg-path">
                            <span class="network-name">${network.walletType}</span>
                            <p class="network-name-info">${network.chainName}</p>
                         </div>
                    </div>`;
        }
        $('.network-27').append(lis);
    }


    /***************************************/

    $(window).bind('beforeunload', function () {
        // 将立即支付订单删除，加入到未支付队列
        let order = localStorage.getItem('to_be_paid_order');
        if (order) {
            order = JSON.parse(order);
            let waitOrder = localStorage.getItem('wait_order');
            let res = waitOrder ? JSON.parse(waitOrder) : {};
            res[order.id] = order;
            localStorage.setItem('wait_order', JSON.stringify(res));
            localStorage.removeItem('to_be_paid_order');
        }
        localStorage.removeItem('pay_orderId');
    });

});