$(function () {
    $(".header-all").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));

    function removeBodyClass() {
        $('#body').removeClass('body-cart');
        $('#checkSubmitInfo').css({'margin-top': '0'});
    }

    let cartMap = sessionStorage.getItem("cartMap");
    if (cartMap) {
        removeBodyClass();
        forItems(JSON.parse(cartMap));
    }

    function forItems(cartMap) {
        let picMap = new Map(JSON.parse(sessionStorage.getItem("picMap")));
        let products = JSON.parse(sessionStorage.getItem("productMap"));
        for (let key in cartMap) {
            let i = parseInt(key);
            let cart = cartMap[i];
            for (let ite of cart) {
                let pros = products[i];
                let product = pros.find(item => item.id == ite.productId);
                let carProduct = `<div class="productBodyItemContent" pi_id="${i}" pId="${product.id}">
                    <em class="checkBox checkIcon"></em>
                    <a class="itemImg" href=""><img src="${picMap.get(i)}"/></a>
                    <div class="itemInfo">
                    <a href="">${product.productName}</a>
                    <div><span>model：${product.model}<i></i></span></div>
                    </div>
                    <div class="itemInfoUnion1">
                    <span class="itemPice">${product.price}</span>
                    </div>
                    <div class="itemInfoUnion2">
                    <div class="itemInfoUnion2Module lelt">
                    <i class="itemMinus item_ma_css l" pi_id="${i}" data-id="${product.id}">-</i>
                    <input type="text" class="itemNum l" value="${ite.quantity}"/>
                    <i class="itemAdd item_ma_css l" pi_id="${i}" data-id="${product.id}">+</i>
                    </div>
                    </div>
                    <div class="itemInfoUnion3">
                    <span class="sumNum">${ite.quantity * product.price}</span>
                    </div>
                    <div class="itemInfoUnion4">
                    <i class="delete" data-id="${product.id}"></i>
                    </div>
                    </div>`;
                $('.productBodyItem').append(carProduct);
            }
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
        let proId = $(this).attr('data-id');
        let pi_id = $(this).attr('pi_id');
        let cars = cartMap[pi_id];
        if (cars) {
            let car = cars.find(item => item.productId == proId);
            if (car) {
                car.quantity += 1;
            }
        }
        sessionStorage.setItem('cartMap', JSON.stringify(cartMap));
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
            let proId = $(this).attr('data-id');
            let pi_id = $(this).attr('pi_id');
            let cars = cartMap[pi_id];
            if (cars) {
                let car = cars.find(item => item.productId == proId);
                if (car) {
                    car.quantity -= 1;
                }
            }
            sessionStorage.setItem('cartMap', JSON.stringify(cartMap));
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
            let proId = $(this).attr('data-id');
            let pi_id = $(this).attr('pi_id');
            let cars = cartMap[pi_id];
            if (cars && cars.length > 0) {
                cars = cars.filter(item => item.productId !== proId);
                if (cars.length === 0) {
                    delete cartMap[pi_id];
                }
            }
            delCartStorage(cartMap);
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
                if (confirm('Are you sure you want to delete all products ?')) {
                    $('.productBodyItemContent').remove();
                    sum = 0;
                    checkBoxArray = [];
                }
                sessionStorage.removeItem('cartMap');
            } else {
                if (confirm('Are you sure you want to delete these items ?')) {
                    numberSort(checkBoxArray);
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        if (sum > 0) {
                            sum -= parseFloat($('.sumNum').eq(checkBoxArray[i]).text());
                        }
                    }
                    let cartMap = JSON.parse(sessionStorage.getItem("cartMap"));
                    for (let i = 0; i < checkBoxArray.length; i++) {
                        // 这里需要减1，删除后index变化
                        let eq = $('.productBodyItemContent').eq(checkBoxArray[i] - i);
                        let pId = eq.attr('pId');
                        let pi_id = eq.attr('pi_id');
                        if (cartMap[pi_id].length > 0) {
                            cartMap[pi_id] = cartMap[pi_id].filter(item => item.productId !== pId);
                            if (cartMap[pi_id].length === 0) {
                                delete cartMap[pi_id];
                            }
                        }
                        eq.remove();
                    }
                    delCartStorage(cartMap);
                    checkBoxArray = [];
                }
            }

            $(".lumpSum").text(sum);
            $("#itemTotal").text(checkBoxArray.length);
        } else {
            confirm('Please select product');
        }
    });

    // 结算
    $('.rightSubmit').click(function () {
        if (checkBoxArray.length === 0) {
            layer.msg("Please select product !");
        } else {
            sessionStorage.setItem('buy_type', 1);
            let order = {};
            for (let i = 0; i < checkBoxArray.length; i++) {
                let eq = $('.productBodyItemContent').eq(checkBoxArray[i]);
                let pId = eq.attr('pId');
                let pi_id = eq.attr('pi_id');
                let obj = order[pi_id];
                let o = {
                    'productId': pId,
                    'quantity': $('.itemNum').eq(checkBoxArray[i]).val()
                };
                if (obj) {
                    let product = obj.find(item => item.productId == pId);
                    if (product) {
                        product.quantity = parseInt(product.quantity) + o.quantity;
                    } else {
                        obj.push(o);
                    }
                } else {
                    order[pi_id] = [o];
                }
            }
            sessionStorage.setItem('buyNow', JSON.stringify(order));
            page('order');
        }
    });

});



