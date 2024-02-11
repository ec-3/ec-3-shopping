let routing = {
    login: '/userInfo/login',
    itemList: '/orderItem/list'
};


function post(url, data, success, fail) {
    let settings = {
        "url": url,
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json",
            "Authorization":localStorage.getItem("token")
        },
        "data": data,
    };
    $.ajax(settings).done(success).fail(fail);
}