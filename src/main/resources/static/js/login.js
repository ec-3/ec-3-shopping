$(function () {
    //引入头部
    $(".floor1").load("/header");


    $(".btn_login").click(function () {
        let username = $(".content_one input.login_number").val();
        let password = $(".content_one input.login_password").val();
        // 登录
        post(routing.login, JSON.stringify({
            "username": username,
            "password": password
        }), function (response) {
            if (response.code === 0) {
                localStorage.setItem("user", JSON.stringify(response.data.user));
                localStorage.setItem("token", response.data.token);
                window.location.replace("/");
                alert('login successful !');
            } else {
                alert(response.msg);
            }
        }, function (error) {
            alert("System response timed out, please try again later ！");
        });
    });

    // 点击手机号登录改变
    $(".floor2 .container .three>a").click(function () {
        // 获取索引
        let idx = $(this).index()
        console.log(idx)
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
