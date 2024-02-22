$(function () {
    $(".header-all").load("/header");
    let cartMap = sessionStorage.getItem("cartMap");
    if (cartMap) {
        forItems(JSON.parse(cartMap));
    } else {
        let user = JSON.parse(sessionStorage.getItem("user"));
        post(routing.cart, JSON.stringify({
            'userId': user['id']
        }), function (response) {
            if (response.code === 0) {
                if (response.data) {
                    sessionStorage.setItem('cartMap', JSON.stringify(response.data));
                    forItems(response.data);
                }
            } else {
                alert(response.msg);
            }
        }, function (error) {
            alert(error_msg + error);
        });
    }

    function forItems(cartMap) {
        for (let key in cartMap) {
            let cart = cartMap[key];
            let picMap = new Map(JSON.parse(sessionStorage.getItem("picMap")));
            let product = JSON.parse(sessionStorage.getItem("productMap"))[key];
            let carProduct = `<div class="productBodyItemContent" pId="${product.id}">
                    <em class="checkBox checkIcon"></em>
                    <a class="itemImg" href=""><img src="${picMap.get(product.id)}"/></a>
                    <div class="itemInfo">
                    <h5>${product.brand}</h5>
                    <a href="">${product.productName}</a>
                    <div><span>规格：${product.specification}<i></i></span></div>
                    </div>
                    <div class="itemInfoUnion1">
                    <span class="itemPice">${product.price}</span>
                    </div>
                    <div class="itemInfoUnion2">
                    <div class="itemInfoUnion2Module lelt">
                    <i class="itemMinus item_ma_css l" data-id="${product.id}">-</i>
                    <input type="text" class="itemNum l" value="${cart.quantity}"/>
                    <i class="itemAdd item_ma_css l" data-id="${product.id}">+</i>
                    </div>
                    </div>
                    <div class="itemInfoUnion3">
                    <span class="sumNum">${cart.quantity * product.price}</span>
                    </div>
                    <div class="itemInfoUnion4">
                    <i class="delete" data-id="${product.id}"></i>
                    </div>
                    </div>`;
            $('.productBodyItem').append(carProduct);
        }
    }

    //商品件数加减
    $("#chekoutBody").on('click', '.itemAdd', function () {
        let index = $('.itemAdd').index(this);
        let num = $('.itemNum').eq(index);
        let sum = $('.sumNum').eq(index);
        let val = num.val();
        val++;
        num.val(val);
        let unitPrice = $('.itemPice').eq(index).text();
        sum.text(val * unitPrice + '.00');
        $('.itemMinus').eq(index).css('color', '#474747');
        let cartMap = JSON.parse(sessionStorage.getItem("cartMap"));
        cartMap[$(this).attr('data-id')].quantity += 1;
        sessionStorage.setItem('cartMap', JSON.stringify(cartMap));
        setUpCart();
        if ($('.checkBox').eq(index + 1).hasClass('checktoggle')) {
            $(".lumpSum").text(parseFloat($(".lumpSum").eq(0).text()) + parseFloat(unitPrice));
        }
    });

    $("#chekoutBody").on('click', '.itemMinus', function () {
        let index = $('.itemMinus').index(this);
        let num = $('.itemNum').eq(index);
        let val = num.val();
        if (val > 1) {
            val--;
            num.val(val);
            let unitPrice = $('.itemPice').eq(index).text();
            $('.sumNum').eq(index).text(val * unitPrice + '.00');
            let cartMap = JSON.parse(sessionStorage.getItem("cartMap"));
            cartMap[$(this).attr('data-id')].quantity -= 1;
            sessionStorage.setItem('cartMap', JSON.stringify(cartMap));
            setUpCart();
            if ($('.checkBox').eq(index + 1).hasClass('checktoggle')) {
                $(".lumpSum").text(parseFloat($(".lumpSum").eq(0).text()) - parseFloat(unitPrice));
            }
        } else {
            $('.itemMinus').eq(index).css('color', '#bdbdbd');
        }
    });

    //删除
    $("#chekoutBody").on('click', '.delete', function () {
        if (confirm('Are you sure you want to delete this item ?')) {
            let index = $('.delete').index(this);
            let cartMap = JSON.parse(sessionStorage.getItem("cartMap"));
            delete cartMap[$(this).attr('data-id')];
            delCartStorage(cartMap);
            setUpCart();
            let sum = parseFloat($(".lumpSum").eq(0).text());
            if (sum > 0) {
                $(".lumpSum").text(sum - parseFloat($('.sumNum').eq(index).text()));
            }
            $('.productBodyItemContent').eq(index).remove();
        }
    });

    function delCartStorage(cartMap) {
        if (Object.keys(cartMap).length === 0) {
            sessionStorage.removeItem('cartMap');
        } else {
            sessionStorage.setItem('cartMap', JSON.stringify(cartMap));
        }
    }

    let checkBoxArray = [];
    //批量选中
    $("body").on('click', '.checkBox', function () {
        let length = $('.checkBox').length;
        let index = $('.checkBox').index(this);
        let checkIcon = 'checkIcon';
        let checkToggle = 'checktoggle';
        let addClass = checkIcon;
        let removeClass = checkToggle;
        let sum = parseFloat($(".lumpSum").eq(0).text());
        if (index === 0 || index === length - 1) {
            if ($('.checkBox').eq(index).hasClass(checkIcon)) {  // 勾选所有
                addClass = checkToggle;
                removeClass = checkIcon;
                sum = 0;
                for (let i = 0; i < length - 2; i++) {
                    checkBoxArray.push(i);
                    sum += parseFloat($('.sumNum').eq(i).text());
                }
            } else {  // 去掉所有勾选
                sum = 0;
                checkBoxArray = [];
            }
            $('.checkBox').addClass(addClass);
            $('.checkBox').removeClass(removeClass);
        } else {
            let x = index - 1;
            let curr_sum = parseFloat($('.sumNum').eq(x).text());
            if (checkBoxArray.includes(x)) {
                checkBoxArray = checkBoxArray.filter(item => item !== x);
                sum -= curr_sum;
            } else {
                checkBoxArray.push(x);
                sum += curr_sum;
            }
            $('.checkBox').eq(index).toggleClass('checkIcon checktoggle');
        }
        $(".lumpSum").text(sum);
        $("#itemTotal").text(checkBoxArray.length);
    });

    //批量删除
    $(".allDelete").click(function () {
        let length = $('.checkBox').length;
        let sum = parseFloat($(".lumpSum").eq(0).text());
        if (checkBoxArray.length > 0) {
            if (checkBoxArray.length === length - 2) {
                if (confirm('确定删除全部商品吗？')) {
                    $('.productBodyItemContent').remove();
                    sum = 0;
                    checkBoxArray = [];
                }
                sessionStorage.removeItem('cartMap');
            } else {
                if (confirm('确定删除这些商品吗？')) {
                    numberSort(checkBoxArray);
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        if (sum > 0) {
                            sum -= parseFloat($('.sumNum').eq(checkBoxArray[i]).text());
                        }
                    }
                    let cartMap = JSON.parse(sessionStorage.getItem("cartMap"));
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        // 这里需要减1，删除后index变化
                        $('.productBodyItemContent').eq(checkBoxArray[i] - i).remove();
                        let pId = $('.productBodyItemContent').eq(checkBoxArray[i]).attr('pId');
                        delete cartMap[pId];
                    }
                    delCartStorage(cartMap);
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        checkBoxArray = checkBoxArray.filter(item => item !== checkBoxArray[i]);
                    }
                }
            }
            $(".lumpSum").text(sum);
            $("#itemTotal").text(checkBoxArray.length);
            setUpCart();
        } else {
            confirm('请勾选商品');
        }
    });

    $(window).bind('beforeunload', function () {
        if (sessionStorage.getItem('upCart')) {
            let user = JSON.parse(sessionStorage.getItem("user"));
            let cart = JSON.parse(sessionStorage.getItem("cartMap"));
            sessionStorage.removeItem('upCart');
            post(routing.execute, JSON.stringify({'userId': user.id, 'cart': cart}));
        }
    });

    function setUpCart() {
        let upCart = sessionStorage.getItem('upCart');
        if (!upCart) sessionStorage.setItem('upCart', true);
        return upCart;
    }

    $('.rightSubmit').click(function () {
        let user = JSON.parse(sessionStorage.getItem("user"));
        if (!user) {
            alert('请登录！');
        } else {
            if (checkBoxArray.length === 0) {
                alert('请选择商品');
            } else {
                sessionStorage.setItem('buy_type', 1);
                let order = {};
                for (let i = 0; i < checkBoxArray.length; i++) {
                    let pId = $('.productBodyItemContent').eq(checkBoxArray[i]).attr('pId');
                    order[pId] = {
                        'userId': user.id,
                        'productId': pId,
                        'quantity': $('.itemNum').eq(checkBoxArray[i]).val()
                    };
                }
                sessionStorage.setItem('buyNow', JSON.stringify(order));
                location.href = pageObj.order;
            }

        }
    });

});

//吸底
window.onload = function () {
    let gocar = document.getElementsByClassName('checkSubmitInfoBooking')[0];
    document.onscroll = function () {
        if (document.documentElement.scrollTop < 411) {
            gocar.style.position = 'fixed';
            gocar.style.bottom = '0';
            gocar.style.boxShadow = '0 0 8px 0 rgba(0,0,0,.1)';
        } else {
            gocar.style.position = 'relative';
            gocar.style.boxShadow = '0 #fff';
        }
    }
};

