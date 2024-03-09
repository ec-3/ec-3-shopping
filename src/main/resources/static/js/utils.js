var ec3Mapping = {
    login: '/userInfo/login',
    register: '/userInfo/register',
    itemList: '/orderItem/list',
    productList: '/productInfo/list',
    cart: '/shopCar/cart',
    execute: '/shopCar/execute',
    submit: '/order/submit',
    address: '/addressGlobal/list',
    address_save: '/addressGlobal/save',
    orderItem: '/orderItem/list',
    order: '/order/list',
    order_update: '/order/update',
    coin: '/coin/coinInfo',
    merchant: '/merchant/chainPayList'
};

function page(name) {
    location.href = pageName(name);
}

function pageName(name) {
    return '/views/' + name + '.html';
}

var error_msg = "System response timed out, please try again later ！: ";

/*$.ajaxSetup({
    beforeSend: function (xhr, settings) {
        //alert('请求前');
    },
    complete: function (xhr, status) {
        //alert('请求完成后');
    }
});*/


function post(url, data, success, fail) {
    let settings = {
        "url": url,
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
           /* "Authorization": sessionStorage.getItem("token")*/
        },
        "data": data
    };
    $.ajax(settings).done(success).fail(fail);
}

function postSync(url, data, success, fail) {
    let settings = {
        "url": url,
        "method": "POST",
        "async": false,
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json",
            "Authorization": sessionStorage.getItem("token")
        },
        "data": data
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


function numberSort(array) {
    array.sort((x, y) => {
        return x - y;
    });
}

function isEmail(email) {
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// 中国大陆手机号
function isPhoneNumber(phone) {
    var phoneNumRegex = /^((\+86)|(0))?\d{11}$/;
    return phoneNumRegex.test(phone);
}

// 手机号做脱敏处理
function phoneHide(phone) {
    let reg = /^(1[3-9][0-9])\d{4}(\d{4}$)/; // 定义手机号正则表达式
    phone = phone.replace(reg, '$1****$2');
    return phone; // 输出为131****1234
}

function javaDate(date) {
    return date.split('.')[0].replace("T", " ");
}
