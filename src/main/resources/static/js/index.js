$(function () {
    $(".header-all").load("/header");
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

    /*	$(".footer-all").load("/views/footer.html");*/

    // 加载商品列表
    post(routing.productList, JSON.stringify({}), function (response) {
        if (response.code === 0) {
            let lis = '';
            let productList = response.data.records;
            localStorage.setItem('keyPics',JSON.stringify(response.data.keyPics));
            localStorage.setItem('productMap',JSON.stringify(response.data.productMap));
            localStorage.setItem('productList',JSON.stringify(productList));
            let picMap = new Map();
            for (let record of productList) {
                lis += ` <li class="fl">
                            <div class="small_phone">
                                <a href="/details">
                                    <span>新品</span>
                                    <div class="box">
                                        <p class="p1">${record.product.productName}</p>
                                        <p class="p1">${record.product.describe}</p>
                                        <p class="p1">${record.product.price}</p>
                                    </div>
                                    <img src="${record.masterUrl}">
                                </a>
                            </div>
                        </li>`;
                picMap.set(record.product.id,record.masterUrl);
            }
            localStorage.setItem('picMap',JSON.stringify(Array.from(picMap.entries())));
            $('#products').append(lis);
        } else {
            alert(response.msg);
        }
    }, function (error) {
        alert("系统错误，请稍后再试！");
    });

});