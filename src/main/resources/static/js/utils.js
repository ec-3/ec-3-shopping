let routing = {
    login: '/userInfo/login',
    itemList: '/orderItem/list',
    productList: '/productInfo/list',
    cart: '/shopCar/cart',
    execute: '/shopCar/execute'
};

let pageObj = {
    cart: '/cart',
    login: '/login',
    index: '/index',
    header: '/header',
    details: '/details',
    register: '/register',
};

let error_msg = "System response timed out, please try again later ！: ";

$.ajaxSetup({
    beforeSend: function (xhr, settings) {
        //alert('请求前');
    },
    complete: function (xhr, status) {
        //alert('请求完成后');
    }
});


function post(url, data, success, fail) {
    let settings = {
        "url": url,
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("token")
        },
        "data": data,
    };
    $.ajax(settings).done(success).fail(fail);
}

function get(url, success, fail) {
    $.ajax({
        "url": url,
        "method": "GET",
        "timeout": 0
    }).done(success).fail(fail);
}

function page_jump(page) {
    let token = localStorage.getItem("token");
    if (token) {
        location.href = page;
    } else {
        alert('please sign in');
        location.href = pageObj.login;
    }
}

function numberSort(array) {
    array.sort((x, y) => {
        return x - y;
    });
}