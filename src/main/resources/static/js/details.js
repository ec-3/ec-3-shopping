$(function () {

    $(".header-all").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));
    /* $(".pic-min ul li:nth-child(1)").click(function () {
         $(".pic-max").find("img").css("display", "none");
         $(".pic-max .max01 img").css("display", "block");
     });
     $(".pic-min ul li:nth-child(2)").click(function () {
         $(".pic-max").find("img").css("display", "none");
         $(".pic-max .max02 img").css("display", "block");
     });
     $(".pic-min ul li:nth-child(3)").click(function () {
         $(".pic-max").find("img").css("display", "none");
         $(".pic-max .max03 img").css("display", "block");
     });*/

    $('.pic-min').on('click', '.lii', function () {
        $(".pic-max").find("img").css("display", "none");
        let select = '.pic-max .max0' + $(this).attr('data') + ' img';
        $(select).css("display", "block");
    });

    //数量选择
    //数量增加
    $(".number-select .num-plus").click(function () {
        var number = $(".number-select input").val();
        var tol = 0;
        if (number < 5) {
            number++;
            $(".number-select input").val(number);
            $(".number-select .num-minus").removeClass("disabled");
            tol = number * 129;
            $(".float-nav .price p").text("￥" + tol + ".00");
        }
        if (number == 5) {
            $(this).addClass("disabled");
        }
    });
    //数量减少
    $(".number-select .num-minus").click(function () {
        var number = $(".number-select input").val();
        var tol = 0;
        if (number > 1) {
            number--;
            $(".number-select input").val(number);
            $(".number-select .num-plus").removeClass("disabled");
            tol = number * 129;
            $(".float-nav .price p").text("￥" + tol + ".00");
        }
        if (number == 1) {
            $(this).addClass("disabled");
        }
    });

    //页面回到顶部
    $(document).scroll(function () {
        var top = $(document).scrollTop();
        if (top > 300) {
            $("#scrolltop").stop().fadeIn("fast");
            $(".float-nav").stop().slideDown("fast");
        } else {
            $("#scrolltop").stop().fadeOut("fast");
            $(".float-nav").stop().slideUp("fast");
        }
    });
    $("#scrolltop").click(function () {
        $("html,body").animate({
            scrollTop: "0"
        }, 500);
    });

    // init details
    let pId;  //  product
    let pi_Id;  //  base product
    let productMap = JSON.parse(localStorage.getItem("productMap"));
    let picMap = new Map(JSON.parse(localStorage.getItem("picMap"))); // 加载图片
    function initDetails() {
        pi_Id = parseInt(localStorage.getItem('pId'));
        pId = localStorage.getItem('pi_Id');
        setDetails();
        setPicture();
        // model
        let as = '';
        let products = productMap[pi_Id];
        for (let p in products) {
            as += ` <a pId="${products[p].id}" class="model">${products[p].model}</a>`;
        }
        $('.type-select').append(as);
    }

    function setDetails() {
        let products = productMap[pi_Id];
        let product = products.find(item => item.id == pId);
        $("#price").text(product.price);
        $("#describe").text(product.describe);
        $("#productName").text(product.productName);
    }

    function setPicture() {
        let keyPics = JSON.parse(localStorage.getItem('keyPics'))[pi_Id];
        let picMax = '';
        let picMin = `<ul>`;
        for (let k in keyPics) {
            if (k == 0) {
                picMax += `<a href="javascript:void(0)" class="max0${k}"><img src="${keyPics[k].picUrl}" width="560px"></a>`;
            } else {
                picMax += `<a href="javascript:void(0)" class="max01"><img   style="display: none" src="${keyPics[k].picUrl}" width="560px"></a>`;
            }
            let li = `<li data="${k}" class="lii"><a href="javascript:void(0)"><img src="${keyPics[k].picUrl}" width="80px"></a></li>`;
            picMin += li;
        }
        $('.pic-max').append(picMax);
        $('.pic-min').append(picMin + `</ul>`);
    }

    initDetails();

    $(".type-select").on('click', '.model', function () {
        pId = $(this).attr('pId');
        setDetails();
        $(this).siblings().css("border", "1px solid #BFBFBF");
        $(this).css("border", "1px solid #000");
    });

    // pay now
    $("#buy_now").click(function () {
        if (please_login('details')) {
            let order = {};
            let obj = order[pi_Id];
            let o = {
                'userId': JSON.parse(localStorage.getItem("user")).id,
                'productId': pId,
                'quantity': $('#quantity').val()
            };
            if (obj) {
                let product = obj.find(item => item.productId == pId);
                if (product) {
                    product.quantity = parseInt(product.quantity) + o.quantity;
                } else {
                    obj.push(o);
                }
            } else {
                order[pi_Id] = [o];
            }
            localStorage.setItem('buyNow', JSON.stringify(order));
            page('order');
        }
    });

    // add cart
    $("#add_cart").click(function () {
        let user = JSON.parse(localStorage.getItem("user"));
        if (!user) {
            please_login();
        } else {
            setUpCart();
            let cart = {'userId': user.id, 'baseId': pi_Id, 'productId': pId, 'quantity': $('#quantity').val()};
            let cartMap = localStorage.getItem("cartMap");
            if (!cartMap) {
                post(ec3Mapping.cart, JSON.stringify({
                    'userId': user.id
                }), function (response) {
                    if (response.code === 0) {
                        setCart(response.data ? response.data : {}, cart);
                    } else {
                        console.log(response.msg);
                    }
                }, function (error) {
                    console.log(error_msg + error);
                });
            } else {
                setCart(JSON.parse(cartMap), cart);
            }
        }
    });

    function setUpCart() {
        let upCart = localStorage.getItem('upCart');
        if (!upCart) localStorage.setItem('upCart', true);
        return upCart;
    }

    function setCart(cartMap, cart) {
        if (cartMap[pi_Id]) {
            let cars = cartMap[pi_Id];
            let car = cars.find(item => item.productId == cart.productId);
            if (car) {
                car.quantity = parseInt(car.quantity) + parseInt(cart.quantity);
            } else {
                cars.push(cart);
            }
        } else {
            cartMap[pi_Id] = [cart];
        }
        localStorage.setItem('cartMap', JSON.stringify(cartMap));
        layer.msg("Added successfully");
    }

    $(window).bind('beforeunload', function () {
        if (localStorage.getItem('upCart')) {
            let user = JSON.parse(localStorage.getItem("user"));
            let cart = JSON.parse(localStorage.getItem("cartMap"));
            localStorage.removeItem('upCart');
            let res = JSON.stringify({'userId': user.id, 'cart': cart});
            post(ec3Mapping.execute, res);
        }
    });


});
