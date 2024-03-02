$(function () {
    $(".header-all").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));
    function initOrder() {
        let user = JSON.parse(localStorage.getItem("user"));
        post(ec3Mapping.order, JSON.stringify({userId: user.id}), function (response) {
            if (response.code === 0) {
                if (response.data) {
                    let tr = '';
                    for (let order of response.data.records) {
                        // 支付状态：0-待支付 1-已支付 2-已取消
                        let statusName = 'pay wait';
                        let paySpan = `<span><a class="pay-now" orderId="${order.id}">pay now</a></span>`;
                        let cancel = `<a class="cancel" style="cursor: pointer;">cancel order</a><br>`;
                        if (order.status == 1) {
                            statusName = 'pay runing';
                            cancel = '';
                            paySpan = '';
                        } else if (order.status == 2) {
                            statusName = 'pay successful';
                            cancel = '';
                            paySpan = '';
                        }
                        // 发货状态：0-待发货 1-部分发货 2-已发货（全部发货）
                        let deliveryStatus = '待发货';
                        if (order.deliveryStatus == 1) {
                            deliveryStatus = '部分发货';
                        } else if (order.deliveryStatus == 2) {
                            deliveryStatus = '已发货';
                        }
                        /* <td>${deliveryStatus}</td>*/
                        tr += `<tr class="tr2">
                            <td> ${order.orderId}</td>
                            <td>${order.totalAmount}</td>
                            <td id="${order.orderId}">${statusName}</td>
                            <td>${javaDate(order.createTime)}</td>
                            <td class="tr2-4">
                            ${paySpan}
                            ${cancel} 
                            <!--<a href="">查看详情</a>-->
                            </td>
                         </tr>`
                    }
                    $('.order-message').append(tr);
                }

            } else {
                alert(response.msg);
            }
        }, function (error) {
            alert(error_msg + error);
        });
    }

    initOrder();

    function payNow(id){

    }

    $(".order-message").on('click', '.pay-now', function () {
        localStorage.setItem('pay_orderId',$(this).attr('orderId'));
        page('pay');
    });

    let count = 60;
    let order = localStorage.getItem('submitted_order');

    function loop_search_tx() {
        if (!localStorage.getItem('submitted_order')) {
            return;
        }
        if (count > 0) {
            search_tx();
            count--;
            setTimeout(loop_search_tx, 5000);
        }
    }

    if (order) {
        order = JSON.parse(order);
        loop_search_tx();
    }

    function search_tx() {
        const provider = new ethers.providers.Web3Provider(window.ethereum);
        provider.getTransactionReceipt(order.chainTx).then((res) => {
            if (res.status === 1 && localStorage.getItem('submitted_order')) {
                order['status'] = 2;
                post(ec3Mapping.order_update, JSON.stringify(order), function (response) {
                    if (response.code === 0) {
                        localStorage.removeItem('submitted_order');
                        $('#' + order.orderId).text('支付成功');
                    } else alert(response.msg);
                }, function (error) {
                    alert(error_msg + error);
                });
            }
        }).catch((err) => {
            //console.error(`交易 ${err} 失败或未处理`);
        });
    }

});
