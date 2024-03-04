$(function () {

    $(".header-all").load(pageName('header'));
    $(".footer-all").load(pageName('footer'));
    let itemSum = 0;
    let current_addr = null;
    let orders = JSON.parse(localStorage.getItem("buyNow"));
    let products = JSON.parse(localStorage.getItem("productMap"));
    let picMap = new Map(JSON.parse(localStorage.getItem("picMap")));

    function initItems() {
        let items = '';
        let itemNum = 0;
        for (let key in orders) {
            let i = parseInt(key);
            let cart = orders[i];
            for (let ite of cart) {
                let pros = products[i];
                let product = pros.find(item => item.id == ite.productId);
                let item = ` <div class="songhuo_bottom">
                    <div class="right">
                        <!-- 主要内容部分 -->
                        <div class="main_info">
                            <!-- 主要内容部分左侧 -->
                            <div class="main_info_left">
                                <div class="sp_img">
                                    <img src="${picMap.get(i)}" alt="">
                                </div>
                                <p>${product.productName}</p>
                                <div class="color_chima">
                                    <div class="chima_right">
                                        <span>model：</span>
                                        <em>${product.model}</em>
                                    </div>
                                </div>
                                <div class="wuliyou_tui">
                                    <div class="tui_img"></div>
                                    <span>Support 7 days no reason return</span>
                                </div>
                            </div>
                            <!-- 主要内容部分右侧 -->
                            <div class="main_info_right">
                                <strong>$ ${product.price}</strong>
                                <span>x${ite.quantity}</span>
                              <!--  <i>有货</i>-->
                            </div>
                        </div>
                    </div>
                </div>`;
                items += item;
                let quantity = parseInt(ite.quantity);
                itemNum += quantity;
                itemSum += quantity * parseFloat(product.price);
            }
        }
        $('#product').append(items);
        $('#itemNum').text(itemNum);
        $('.itemSum').text(itemSum);
    }

    // 加载订单商品
    initItems();

    // 加载地址
    function initAddress() {
        if (!check_addr_storage()) {
            let user = JSON.parse(localStorage.getItem("user"));
            post(ec3Mapping.address, JSON.stringify({userId: user.id}), function (response) {
                let address = '';
                if (response.code === 0) {
                    add_addr_item(response.data);
                } else {
                    console.log(response.msg);
                }
            }, function (error) {
                console.log(error_msg + error);
            });
        }
    }

    function check_addr_storage() {
        let address = localStorage.getItem("address");
        let defAddr = localStorage.getItem("def_addr");
        let addr_html = '';
        if (defAddr) {
            let addr = JSON.parse(defAddr);
            addr_html = add_addr(addr);
            setAddress(addr);
        }
        if (address) {
            address = JSON.parse(address);
            for (let addr of address) {
                addr_html += add_addr(addr);
            }
        }
        $('.shouhuo_bottom').append(addr_html);
        return address || defAddr;
    }

    function add_addr_item(addrs) {
        let address = '';
        let addr_array = [];
        for (let addr of addrs) {
            if (addr.defFlag !== 1) {
                addr_array.push(addr);
            } else {
                setAddress(addr);
                localStorage.setItem("def_addr", JSON.stringify(addr));
            }
            address += add_addr(addr);
        }
        localStorage.setItem("address", JSON.stringify(addr_array));
        $('.shouhuo_bottom').append(address);
    }

    function add_addr(addr) {
        let default_address = addr.defFlag === 1 ? '<span class="moren_biaoshi">default address</span>' : '';
        return `<div class="shouhuo_address">
                       <a class="address_left" addrId="${addr.id}" href="javascript:;">
                           <span>${addr.label}</span>
                           <em></em>
                       </a>
                       <div class="address_right">
                           <span class="uName">${addr.recipient}</span>
                           <span class="uAddress">${addr.address}</span>
                           <span class="uPhone">${addr.phone}</span>
                           ${default_address}
                           <a class="dele" href="javascript:;">delete</a>
                           <a class="bianji" href="javascript:;">edit</a>
                           <a class="default_address" href="javascript:;">set as Default address</a>
                       </div>
                   </div>`;

    }

    initAddress();

    function check() {
        if (!localStorage.getItem("address") || !localStorage.getItem("def_addr")) {
            layer.msg('Please add shipping address !');
            return false;
        }
        return true;
    }

    // 提交订单
    $('.btn_submit').click(function () {
        if (check()) {
            let order = createOrder();
            let cartMap = JSON.parse(localStorage.getItem("cartMap"));
            let buyNow = JSON.parse(localStorage.getItem("buyNow"));
            let buyType = localStorage.getItem('buy_type');
            if (buyType) {
                order['submitType'] = 1;
                let ids = [];
                for (let id in buyNow) {
                    ids.push(cartMap[id].id);
                }
                order['carIds'] = ids;
            }
            post(ec3Mapping.submit, JSON.stringify(order), function (response) {
                if (response.code === 0) {
                    layer.msg('Orders submitted successfully！');
                    localStorage.setItem('to_be_paid_order', JSON.stringify(response.data));
                    if (buyType) {
                        for (let id in buyNow) {
                            delete cartMap[id];
                        }
                        if (Object.keys(cartMap).length === 0) {
                            localStorage.removeItem('cartMap');
                        } else {
                            localStorage.setItem("cartMap", cartMap);
                        }
                    }
                    localStorage.removeItem('buy_type');
                    page('pay');
                } else {
                    console.log(response.msg);
                }
            }, function (error) {
                console.log(error_msg + error);
            });
        }
    });

    function createOrder() {
        let addr = current_addr ? current_addr :
            JSON.parse(localStorage.getItem('def_addr'));
        let user = JSON.parse(localStorage.getItem("user"));
        let userId = user.id;
        let amId = addr.id;               // 收货地址ID
        let userName = user.username;
        let totalAmount = itemSum;        // 订单总金额
        let payAmount = itemSum;          // 应付金额
        let payType = 1;                  // 支付方式：链上支付
        let recipient = addr.recipient;   // 收货人
        let mobile = addr.phone;          // 手机号
        let orderItems = [];

        for (let key in orders) {
            let i = parseInt(key);
            let buy = orders[key];
            for (let ite of buy) {
                let pros = products[i];
                let pro = pros.find(item => item.id == ite.productId);
                let item = {
                    userId: userId,
                    productId: key,
                    masterPic: picMap.get(i),
                    productName: pro.productName,
                    quantity: ite.quantity,
                    price: pro.price
                };
                orderItems.push(item);
            }
        }

       /* for (let key in orders) {
            let buy = orders[key];
            let pro = products.find(item => item.id == buy.productId);
            let item = {
                userId: userId,
                productId: key,
                masterPic: picMap.get(parseInt(key)),
                productName: pro.productName,
                quantity: buy.quantity,
                price: pro.price
            };
            orderItems.push(item);
        }*/
        let order = {
            userId: userId,
            amId: amId,
            userName: userName,
            totalAmount: totalAmount,
            payAmount: payAmount,
            payType: payType,
            recipient: recipient,
            mobile: mobile
        };
        return {
            order: order,
            orderItems: orderItems
        };
    }

    $(".shouhuo_address .address_right").hover(function () {
        $(this).css("backgroundColor", "#fff3f3");
        $(this).find(".default_address").show();
        $(this).find(".bianji").show();
        $(this).find(".dele").show();
    }, function () {
        $(this).css("backgroundColor", "#fff");
        $(this).find(".default_address").hide();
        $(this).find(".bianji").hide();
        $(this).find(".dele").hide();
    });

    // 选择收货信息
    $(".address_left").click(function () {
        let index = $('.address_left').index(this);
        if ((!current_addr && index !== 0) || (current_addr && current_addr.index !== index)) {
            $(this).css("border", "2px solid #e4393c");
            $(this).find("em").show();
            let k = current_addr ? current_addr.index : 0;
            let addrId = $(this).attr('addrId');
            let defAddr = JSON.parse(localStorage.getItem("def_addr"));
            let addrBase = JSON.parse(localStorage.getItem("address"));
            current_addr = addrBase.find(item => item.id == addrId);
            if (!current_addr) {
                current_addr = defAddr;
            }
            $('.address_left').eq(k).css("border", "2px solid #dddddd").find("em").hide();
            current_addr['index'] = index;
            current_addr['amId'] = addrId;
            setAddress(current_addr);
        }
    });

    function setAddress(addr) {
        $('#b_name').text(addr.recipient.substring(0, 1));
        $('#b_phone').text(phoneHide(addr.phone));
        $('#b_address').text(addr.address);
    }

    // 支付方式样式切换
    $(".zifu_a").click(function () {
        $(this).css("border", "2px solid #e4393c").siblings().css("border", "2px solid #dddddd");
        $(this).find("i").show();
        $(this).siblings().find("i").hide();
    });

    /*    // 设置为默认地址
            $(".shouhuo_address:first .address_right .moren_biaoshi").show();
            $(".default_address").click(function () {
                $(this).siblings(".moren_biaoshi").show();
                $(this).parents(".shouhuo_address").siblings().find(".address_right .moren_biaoshi").hide();
            });*/

    // 删除地址
    $(".dele").click(function () {
        if (confirm("您确认要删除该收货地址吗？")) {
            $(this).parents(".shouhuo_address").remove();
        }
    });

    // 点击打开新增收货地址框
    $(".shouhuo_top a").click(function () {
        $(".reset").click();
        isAdd = true;
        $(".xuan_name .address_text").val("");
        $(".xuan_shou_address .address_text").val("");
        $(".xuan_phone .phone_text").val("");
        $(".xuan_gu_phone .gu_phone_text").val("");
        $(".xuan_email .email_text").val("");
        $(".xuan_address_bie_text").val("");
        $(".add_address_big").show();
    });

    // 点击关闭新增收货地址框
    $(".address_top_title .close_box").click(function () {
        $(".add_address_big").hide();
    });

    // 地址别名常用名称点击赋值
    $(".changyong_name").click(function () {
        $(".xuan_address_bie_text").val($(this).text());
    })

    let isAdd = true;

    // 保存收货人信息
    $(".baocun_info").click(function () {
        let user = JSON.parse(localStorage.getItem("user"));
        let recipient = $("#Receiver").val();     //  收货人
        let phone = $("#Phone").val();     // 手机号
        let country = $("#Country").val();   // 所在地区
        let city = $("#Town").val();    //  Town / City 城镇
        let street = $("#Street").val();    //街道地址
        let county = $("#State").val();     // State / County 州 / 县
        let postcode = $("#Postcode").val();   // 邮政编码
        let email = $("#Email_address").val();   //  邮箱地址
        let address = $("#detailed_address").val();   //  详细地址
        let label = $("#address_alias").val();     //  地址别名

        let addressGlobal = {
            defFlag: 0, userId: user.id, recipient: recipient,
            phone: phone, country: country, city: city, street: street,
            county: county, postcode: postcode, email: email, address: address, label: label
        };
        post(ec3Mapping.address_save, JSON.stringify(addressGlobal), function (response) {
            if (response.code === 0) {
                layer.msg('added successfully !');
                $(".add_address_big").hide();
                let addr_array = JSON.parse(localStorage.getItem("address"));
                addr_array.push(addressGlobal);
                localStorage.setItem("address", JSON.stringify(addr_array));
                $('.shouhuo_bottom').append(add_addr(addressGlobal));
            }
        }, function (error) {
            console.log(error_msg + error);
        });

    });
    let i = 0;
    // 点击编辑修改地址
    $(".bianji").click(function () {
        i = $(this).parents(".shouhuo_address").index();
        isAdd = false;
        $(".confirm").click();
        $(".add_address_big").show();
        $("#bigOne #totalConfirm").val($(this).siblings(".uAddress").text());
        $(".xuan_name .address_text").val($(this).siblings(".uName").text());
        $(".xuan_shou_address .address_text").val($(this).siblings(".u_gu_address").text());
        $(".xuan_phone .phone_text").val($(this).siblings(".uPhone").text());
        $(".xuan_gu_phone .gu_phone_text").val($(this).siblings(".uPhone").text());
        $(".xuan_email .email_text").val("517663950@qq.com");
        if ($(this).parent().siblings(".address_left").find("span").text() === "家里" || $(this).parent().siblings(".address_left").find("span").text() === "父母家" || $(this).parent().siblings(".address_left").find("span").text() === "公司") {
            $(".xuan_address_bie_text").val($(this).parent().siblings(".address_left").find("span").text());
        } else {
            $(".xuan_address_bie_text").val($(this).siblings(".uName").text());
        }
    });
});