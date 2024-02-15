$(function () {
    // 猜你喜欢
    $.each(car, function (index, data) {
        let carProduct = `<div class="longBoxContentLi l">
						<div class="longBoxContentLiImg">
							<img src="${data.src1}"/>
						</div>
						<div class="longBoxContentLiInfo">
							<div class="infoTag">${data.name1}</div>
							<div class="infoName">${data.name2}</div>
							<div class="infoPrice">${data.price}</div>
						</div>
					</div>`;
        $('.longBoxContent').append(carProduct)

    });

    // 加载购物车
    let cartMap = JSON.parse(localStorage.getItem("cartMap"));
    if (cartMap) {
        forItems(cartMap);
    } else {
        let user = JSON.parse(localStorage.getItem("user"));
        post(routing.cart, JSON.stringify({
            'userId': user['id']
        }), function (response) {
            if (response.code === 0) {
                localStorage.setItem('cartMap', JSON.stringify(response.data));
                forItems(response.data);
            } else {
                alert(response.msg);
            }
        }, function (error) {
            alert("System response timed out, please try again later ！");
        });
    }

    function forItems(cartMap) {
        for (let key in cartMap) {
            let cart = cartMap[key];
            let picMap = new Map(JSON.parse(localStorage.getItem("picMap")));
            let product = JSON.parse(localStorage.getItem("productMap"))[key];
            let carProduct = `<div class="productBodyItemContent">
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
                    <i class="itemMinus item_ma_css l"data-id="${product.id}">-</i>
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
        let cartMap = JSON.parse(localStorage.getItem("cartMap"));
        cartMap[$(this).attr('data-id')].quantity += 1;
        localStorage.setItem('cartMap', JSON.stringify(cartMap));
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
            let cartMap = JSON.parse(localStorage.getItem("cartMap"));
            cartMap[$(this).attr('data-id')].quantity -= 1;
            localStorage.setItem('cartMap', JSON.stringify(cartMap));
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
            let cartMap = JSON.parse(localStorage.getItem("cartMap"));
            delete cartMap[$(this).attr('data-id')];
            localStorage.setItem('cartMap', JSON.stringify(cartMap));
            setUpCart();
            let sum = parseFloat($(".lumpSum").eq(0).text());
            if (sum > 0) {
                $(".lumpSum").text(sum - parseFloat($('.sumNum').eq(index).text()));
            }
            $('.productBodyItemContent').eq(index).remove();
        }
    });

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
            } else {
                if (confirm('确定删除这些商品吗？')) {
                    numberSort(checkBoxArray);
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        if (sum > 0) {
                            sum -= parseFloat($('.sumNum').eq(checkBoxArray[i]).text());
                        }
                    }
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        // 这里需要减1，删除后index变化
                        $('.productBodyItemContent').eq(checkBoxArray[i] - i).remove();
                    }
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        checkBoxArray = checkBoxArray.filter(item => item !== checkBoxArray[i]);
                    }
                }
            }
            $(".lumpSum").text(sum);
            $("#itemTotal").text(checkBoxArray.length);
        } else {
            confirm('请勾选商品');
        }
    });

    //水平轮播
    let i = 0;
    $('.controlLeft').click(function () {
        i--;
        if (i == 0) {
            $('.controlLeft').css('background-position', '0 0');
        }
        if (i < 0) {
            i = 0;
        }
        $('.controlRight').css('background-position', '-50px -60px');
        changePic(i);
    })
    $('.controlRight').click(function () {
        i++;
        $('.controlLeft').css('background-position', '0 -60px');
        if (i == 2) {
            $('.controlRight').css('background-position', '-50px 0');
        }
        if (i > 2) {
            i = 2;
        }
        changePic(i);
    })

    function changePic() {
        let nowLeft = -i * 1190;
        $('.longBoxContent').css('left', nowLeft);
    }

    $(window).bind('beforeunload', function () {
        alert('离开购物车页面');
        let upCart = setUpCart();
        if (upCart) {
            let cartMap = JSON.parse(localStorage.getItem("cartMap"));
            post(routing.execute, cartMap, function (response) {
                if (response.code === 0) {

                } else {
                    alert(response.msg);
                }
            }, function (error) {
                alert(error_msg + error);
            });
        }
    });

    function setUpCart() {
        let upCart = localStorage.getItem('upCart');
        if (!upCart) localStorage.setItem('upCart', true);
        return upCart;
    }

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

