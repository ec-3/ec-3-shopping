$(function () {
    /*$(".header-all").load(pageName('header'));*/
    $(".footer-all").load(pageName('footer'));
    var swiper = new Swiper(".swiper-container", {
        loop: true,
        autoplay: true,
        // effect: 'fade',
        autoplay: {
            disableOnInteraction: false,
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true
        },
    });

    $(".section li").hover(function () {
        $(this).find("img").stop().animate({
            marginTop: "-10px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            marginTop: "10px",
        })
    });

    $(".phone_banner").hover(function () {
        $(this).find("img").stop().animate({
            // marginTop:"-10px",
            width: "2700px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            // marginTop:"10px",
            width: "2560px",
        })
    });

    $(".xinpin li .big_phone").hover(function () {
        $(this).find("img").stop().animate({
            // marginTop:"-10px",
            width: "500px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            // marginTop:"10px",
            width: "460px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });

    $(".xinpin li .small_phone").hover(function () {
        $(this).find("img").stop().animate({
            // marginTop:"-10px",
            width: "260px",
            marginLeft: "-15px",
            marginTop: "-15px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            // marginTop:"10px",
            width: "230px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });

    $(".content .remai li:nth-child(1)").hover(function () {
        $(this).find("img").stop().animate({
            // marginTop:"-10px",
            width: "340px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            // marginTop:"10px",
            width: "303px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });
    $(".content .remai li:nth-child(5)").hover(function () {
        $(this).find("img").stop().animate({
            // marginTop:"-10px",
            width: "340px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            // marginTop:"10px",
            width: "303px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });
    $(".content .remai li:nth-child(9)").hover(function () {
        $(this).find("img").stop().animate({
            // marginTop:"-10px",
            width: "340px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).find("img").stop().animate({
            // marginTop:"10px",
            width: "303px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });

    $(".retie li a").hover(function () {
        $(this).children("img").stop().animate({
            // marginTop:"-10px",
            width: "340px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).children("img").stop().animate({
            // marginTop:"10px",
            width: "303px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });

    $(".flyme li:first-child a").hover(function () {
        $(this).children("img").stop().animate({
            // marginTop:"-10px",
            width: "655px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).children("img").stop().animate({
            // marginTop:"10px",
            width: "625px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });

    $(".shipin li:first-child a").hover(function () {
        $(this).children("img").stop().animate({
            // marginTop:"-10px",
            width: "340px",
            marginLeft: "-20px",
            marginTop: "-20px",
        })
    }, function () {
        $(this).children("img").stop().animate({
            // marginTop:"10px",
            width: "303px",
            marginLeft: "0px",
            marginTop: "0px",
        })
    });

    $(".swiper-wrapper").on('click', '.swiper-slide', function () {
        localStorage.setItem('pId', $(this).attr('pId'));
        localStorage.setItem('pi_Id', $(this).attr('id'));
        page('details');
    });

    function forProItems(productList) {
        let divs = '';
        let picMap = new Map();
        for (let record of productList) {
            divs += `<div id="${record.product.id}" pId="${record.product.productId}" class="swiper-slide"
 style="height: 100%;background: url(${record.masterUrl}) no-repeat center top;background-size: cover;"></div>`;
            picMap.set(record.product.productId, record.masterUrl);
        }
        $('.swiper-wrapper').append(divs);
        localStorage.setItem('picMap', JSON.stringify(Array.from(picMap.entries())));
    }

    // 加载商品列表
    let productList = localStorage.getItem("productList");
    if (productList) {
        forProItems(JSON.parse(productList));
    } else {
        post(ec3Mapping.productList, JSON.stringify({}), function (response) {
            if (response.code === 0) {
                let data = response.data;
                if (data) {
                    if (Object.keys(data.keyPics).length > 0) {
                        localStorage.setItem('keyPics', JSON.stringify(data.keyPics));
                    }
                    if (Object.keys(data.productMap).length > 0) {
                        localStorage.setItem('productMap', JSON.stringify(data.productMap));
                    }
                }
                productList = data.records;
                if (productList && productList.length > 0) {
                    localStorage.setItem('productList', JSON.stringify(productList));
                    forProItems(productList);
                }
            } else {
                console.log(response.msg);
            }
        }, function (error) {
            console.log(error_msg + error);
        });
    }
});