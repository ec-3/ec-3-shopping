$(function () {
    $(".header-all").load("/header");

    $(".btn_login").click(function () {
        let login = $("#login").val();
        let value = '';
        if (isEmail(login)) {
            value = 'email';
        } else if (isPhoneNumber(login)) {
            value = 'mobile';
        }
        if (value === '') {
            alert('Email or mobile phone number format is wrong!');
        } else {
            let user = {};
            user[value] = login;
            user['password'] = $("#password").val();
            // 登录
            post(routing.login, JSON.stringify(user), function (response) {
                if (response.code === 0) {
                    sessionStorage.setItem("user", JSON.stringify(response.data.user));
                    sessionStorage.setItem("token", response.data.token);
                    window.location.replace("/");
                    alert('login successful !');
                } else {
                    alert(response.msg);
                }
            }, function (error) {
                alert(error_msg + error);
            });
        }

    });

    // 点击手机号登录改变
    $(".floor2 .container .three>a").click(function () {
        // 获取索引
        let idx = $(this).index()
        if (idx == 0) {
            $(".content_two").show();
            $(".content_one").hide();
            $(".none").show();
            $(".show").hide();
        } else {
            $(".content_two").hide();
            $(".content_one").show();
            $(".none").hide();
            $(".show").show();
        }
    })
    // 右上角改变事件
    $(".right .picture").click(function () {
        $(".right").stop().slideToggle()
    })
})
