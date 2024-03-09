$(function () {

    $(".header-all").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));

    $('.pic-min').on('click', '.lii', function () {
        $(".pic-max").find("img").css("display", "none");
        let select = '.pic-max .max0' + $(this).attr('data') + ' img';
        $(select).css("display", "block");
    });

    //num add
    $(".number-select .num-plus").click(function () {
        var number = $(".number-select input").val();
        var tol = 0;
        if (number < 5) {
            number++;
            $(".number-select input").val(number);
            $(".number-select .num-minus").removeClass("disabled");
            tol = number * 129;
            $(".float-nav .price p").text("$" + tol + ".00");
        }
        if (number == 5) {
            $(this).addClass("disabled");
        }
    });
    //num minus
    $(".number-select .num-minus").click(function () {
        var number = $(".number-select input").val();
        var tol = 0;
        if (number > 1) {
            number--;
            $(".number-select input").val(number);
            $(".number-select .num-plus").removeClass("disabled");
            tol = number * 129;
            $(".float-nav .price p").text("$" + tol + ".00");
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
    let productMap = JSON.parse(sessionStorage.getItem("productMap"));
    let picMap = new Map(JSON.parse(sessionStorage.getItem("picMap"))); // 加载图片
    function initDetails() {
        pi_Id = parseInt(sessionStorage.getItem('pId'));
        pId = sessionStorage.getItem('pi_Id');
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
        let keyPics = JSON.parse(sessionStorage.getItem('keyPics'))[pi_Id];
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
            let order = {};
            let obj = order[pi_Id];
            let o = {
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
            sessionStorage.setItem('buyNow', JSON.stringify(order));
            page('order');
    });

    // add cart
    $("#add_cart").click(function () {
            let cart = {'baseId': pi_Id, 'productId': pId, 'quantity': $('#quantity').val()};
            let cartMap = sessionStorage.getItem("cartMap");
            setCart(JSON.parse(cartMap), cart);
    });

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
        sessionStorage.setItem('cartMap', JSON.stringify(cartMap));
        layer.msg("Added successfully");
    }



});
