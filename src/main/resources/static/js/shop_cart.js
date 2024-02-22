//购物车的js代码
//alert("由于版本开发原因当前加入购物车选项只支持加入鼠标到购物车里面")
$(function(){
    // 猜你喜欢模块轮播
    $(".lick_list .btn_right").click(function(){
        $(".lick_list ul").animate({
            left: -987
        })
    })
    $(".lick_list .btn_left").click(function(){
        $(".lick_list ul").animate({
            left: 0
        })
    })
    // 购物车商品数量加减
    $(".sp_num .jia").click(function(){
        var n = $(this).siblings("input").val();
        n++;
        $(this).siblings("input").val(n);
        // 当前商品价格
        var p = $(this).parent().siblings(".sp_price").html();
        p = p.substr(1);
        // 小计
        $(this).parent().siblings(".sp_xiaoji").html("￥"+(p*n).toFixed(2));
        getSum();
    })
    $(".sp_num .jian").click(function(){
        var n = $(this).siblings("input").val();
        if(n==1){
            // $(this).css("cursor","not-allowed");
            return false;
        }
        n--;
        $(this).siblings("input").val(n);
         // 当前商品价格
         var p = $(this).parent().siblings(".sp_price").html();
         p = p.substr(1);
         // 小计
         $(this).parent().siblings(".sp_xiaoji").html("￥"+(p*n).toFixed(2));
         getSum();
        // $(this).css("cursor","pointer");
    })
    // 用户文本框输入值，重新计算小计
    $(".sp_num input").change(function(){
        var n = $(this).val();
        var p = $(this).parent().siblings(".sp_price").html();
        p = p.substr(1);
        $(this).parent().siblings(".sp_xiaoji").html("￥"+(p*n).toFixed(2));
        getSum();
    })
    getSum();
    // 计算总件数和总价钱
    function getSum(){
        var count=0;//计算总件数
        var money=0;//计算总价钱
        $(".sp_num input").each(function(i,ele){
            count+=parseInt($(ele).val());
        })
        $(".yixuan_num em").text(count);
        $(".cart_bar_left span").text(count);
        $(".shopcar span").text(count);
        $(".sp_xiaoji").each(function(i,ele){
            money+=parseFloat($(ele).text().substr(1));
        })
        $(".zong_money em").text("￥"+money.toFixed(2));
        console.log("当前的总件数有：" + count + "件");
    }
    // 商品后面的删除按钮
    $(".delete_gz .delete_text").click(function(){
        $(this).parents(".cart_list").remove();
        getSum();
    })
    // 删除选中的商品
    $(".jiesuan_big").find("a").eq(0).click(function(){
        $(".cart_list>input:checked").parents(".cart_list").remove();
        getSum();
    })
    // 清理购物车
    $(".jiesuan_big").find("a").eq(2).click(function(){
        $(".cart_list").remove();
        getSum();
    })
    // 全选  全不选
    $(".checkall").change(function(){
        $(".j_checkall, .checkall").prop("checked",$(this).prop("checked"));
        if($(this).prop("checked")){
            $(".cart_list").css("backgroundColor","#fff4e8");
        }else{
            $(".cart_list").css("backgroundColor","#fff");
        }
        getSum();
    })
    $(".j_checkall").change(function(){
        if(($(".j_checkall:checked").length)===$(".j_checkall").length){
            $(".checkall").prop("checked",true);
        }else{
            $(".checkall").prop("checked",false);
        }
        if($(this).prop("checked")){
            $(this).parents(".cart_list").css("backgroundColor","#fff4e8");
        }else{
            $(this).parents(".cart_list").css("backgroundColor","#fff");
        }
        getSum();
    })
    // 猜你喜欢模块点击加入购物车
    // 如果需要设定只允许某一个商品加入购物车的话在li后面加:eq(n)，n为第几个商品 从0算起
    // 判断语句 如果下方猜你喜欢中的商品在上方购物车里面出现过的话 就在上方的购物车数量+1
    $(".lick_list ul li .add_jia").click(function(){
        $(".gouwu_sp_box .cart_list:last").after($(".gouwu_sp_box .cart_list:last").clone(true));
        $(".gouwu_sp_box .cart_list:last .sp_img img").prop("src",$(this).siblings(".a_img").find("img").prop("src")).css("width","100%");;
        $(".gouwu_sp_box .cart_list:last .sp_info_text .sp_name").text($(this).siblings("p").text());
        $(".gouwu_sp_box .cart_list:last .zengpin_big .zengpin").remove();
        $(".gouwu_sp_box .cart_list:last .banben_color .color_text").text("儒雅黑");
        $(".gouwu_sp_box .cart_list:last .banben_color .banben_text").text("");
        $(".gouwu_sp_box .cart_list:last .sp_price").text($(this).siblings(".sp_price").text());
        $(".gouwu_sp_box .cart_list:last .sp_xiaoji").text($(this).siblings(".sp_price").text());
        getSum();
    })
})