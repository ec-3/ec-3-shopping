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
                    <div class="itemInfoUnion2Module l">
                    <i class="itemMinus item_ma_css"data-id="${product.id}">-</i>
                    <input type="text" class="itemNum" value="${cart.quantity}"/>
                    <i class="itemAdd item_ma_css" data-id="${product.id}">+</i>
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
        let add = $('.itemAdd').index(this);
        var num = $('.itemNum').eq(add);
        var sum = $('.sumNum').eq(add);
        let val = num.val();
        val++;
        num.val(val);
        let unitPrice = $('.itemPice').eq(add).text();
        sum.text(val * unitPrice + '.00');
        $('.itemMinus').eq(add).css('color', '#474747');
        let cartMap = JSON.parse(localStorage.getItem("cartMap"));
        cartMap[$(this).attr('data-id')].quantity += 1;
        localStorage.setItem('cartMap', JSON.stringify(cartMap));
    });

    $("#chekoutBody").on('click', '.itemMinus', function () {
        let min = $('.itemMinus').index(this);
        var num = $('.itemNum').eq(min);
        let val = num.val();
        if (val > 1) {
            val--;
            num.val(val);
            let UnitPrice = $('.itemPice').eq(min).text();
            $('.sumNum').eq(min).text(val * UnitPrice + '.00');
            let cartMap = JSON.parse(localStorage.getItem("cartMap"));
            cartMap[$(this).attr('data-id')].quantity -= 1;
            localStorage.setItem('cartMap', JSON.stringify(cartMap));
        } else {
            $('.itemMinus').eq(min).css('color', '#bdbdbd');
        }
    });

    //删除
    $("#chekoutBody").on('click', '.delete', function () {
        if (confirm('Are you sure you want to delete this item ?')) {
            let cartMap = JSON.parse(localStorage.getItem("cartMap"));
            delete cartMap[$(this).attr('data-id')];
            localStorage.setItem('cartMap', JSON.stringify(cartMap));
            $('.productBodyItemContent').eq($('.delete').index(this)).remove();
        }
    });

    //批量删除
    $("#chekoutBody").on('click', '.checkBox', function () {
        let index = $('.checkBox').index(this);
        console.log(index)
        if (index == 0 || index == 4) {
            $('.checkBox').toggleClass('checkIcon checktoggle')
        } else {
            $('.checkBox').eq(index).toggleClass('checktoggle checkIcon')
        }
        $('.allDelete').click(function () {
            if (index == 0 || index == 4) {
                let b = confirm('确定删除全部商品吗？')
                if (b == true) {
                    console.log(index)
                    $('.productBodyItemContent').remove()
                } else {
                    console.log(11111)
                }
            } else {
                let a = confirm('确定删除这件商品吗？')
                let alldelete = index - 1;
                if (a == true) {
                    console.log(alldelete)
                    $('.productBodyItemContent').eq(alldelete).remove()
                } else {
                    console.log(222);
                }
            }
        })
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
        console.log('前' + i)
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

        console.log('后' + i);
        changePic(i);
    })

    function changePic() {
        let nowLeft = -i * 1190;
        console.log(nowLeft)
        $('.longBoxContent').css('left', nowLeft);
    }
});

//吸底
window.onload = function () {
    let gocar = document.getElementsByClassName('checkSubmitInfoBooking')[0];
    document.onscroll = function () {
        console.log(document.documentElement.scrollTop)
        if (document.documentElement.scrollTop < 411) {
            gocar.style.position = 'fixed';
            gocar.style.bottom = '0';
            gocar.style.boxShadow = '0 0 8px 0 rgba(0,0,0,.1)';
        } else {
            gocar.style.position = 'relative';
            gocar.style.boxShadow = '0 #fff';
        }
    }

}

