$(function () {
    $(".header").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));
    // 注册
    $("#personal_reg").click(function () {
        let email = $("#email").val();
        let password = $("#password").val();
        let passAgain = $("#pass_again").val();
        if (check_account(email, password, passAgain)) {
            post(ec3Mapping.register, JSON.stringify({
                email: email,
                password: password,
            }), function (response) {
                if (response.code === 0) {
                    alert('registration successful !');
                    location.replace("/login");
                } else {
                    alert(response.msg);
                }
            }, function (error) {
                alert(error_msg + error);
            });
        }

    });

    function check_account(email, password, passAgain) {
        if (!email && !password) {
            alert('请输入Email以及密码');
            return false;
        }
        if (!/^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/.test(email)) {
            alert("请输入正确的邮箱");
            return false;
        }
        if (!/^\w{8,20}$/.test(password)) {
            alert("请重新输入,密码由8-20位组成，包括大写，小写，数字组成");
            return false;
        }
        //判断两次输入密码是否一致
        if (password !== passAgain) {
            alert("两次输入的密码不一致");
            return false;
        }
        return true;
    }
});
