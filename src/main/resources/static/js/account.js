$(function () {
    $(".header-all").load("/header");

    function initOrder() {
        let user = JSON.parse(sessionStorage.getItem("user"));
        post(routing.order, JSON.stringify({userId: user.id}), function (response) {
            if (response.code === 0) {
                if (response.data) {
                    let tr = '';
                    for (let order of response.data.records) {
                        // 支付状态：0-待支付 1-已支付 2-已取消
                        let statusName = '待支付';
                        if (order.status == 1) {
                            statusName = '已支付';
                        } else if (order.status == 2) {
                            statusName = '已取消';
                        }
                        // 发货状态：0-待发货 1-部分发货 2-已发货（全部发货）
                        let deliveryStatus = '待发货';
                        if (order.deliveryStatus == 1) {
                            deliveryStatus = '部分发货';
                        } else if (order.deliveryStatus == 2) {
                            deliveryStatus = '已发货';
                        }
                        tr += `<tr class="tr2">
                            <td> ${order.orderId}</td>
                            <td>${order.totalAmount}</td>
                            <td>${statusName}</td>
                            <td>${deliveryStatus}</td>
                            <td>${javaDate(order.createTime)}</td>
                            <td class="tr2-4">
                            <span><a href="">立即付款</a></span>
                             <a class="cancel" style="cursor: pointer;">取消订单</a><br>
                            <a href="">查看详情</a>
                            </td>
                         </tr>`
                    }
                 $('.order-message').append(tr);
                }

            } else {
                alert(response.msg);
            }
        }, function (error) {
            alert(error_msg + error);
        });
    }

    initOrder();

});
