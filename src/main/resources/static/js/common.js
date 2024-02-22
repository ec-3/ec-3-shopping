// 顶部导航栏的js代码
$(function(){
    // 顶部广告
    $(".header_qx .close").click(function(){
        $(".header_qx").css("display","none")
    })
    // 快捷导航地址下拉菜单
   $(".fl").hover(function(){
       $(".fl .address").toggle();
   }) 
   $(".china_address a").on({
       mouseover: function(){
            $(this).css({"background":"#f4f4f4","color":"#c81623"});
       },
       mouseout: function(){
            $(this).css({"background":"#fff","color":"#999999"});
       },
       click: function(){
        $(".fl>ul li a").html($(this).html());
       }
   })
//    快捷导航我的京东下拉菜单
    $(".my_jd").hover(function(){
       $(".my_cd").toggle();
   }) 
   //    快捷导航企业采购下拉菜单
   $(".cg").hover(function(){
    $(".cg_cd").toggle();
}) 
   //    快捷导航客户服务下拉菜单
   $(".fw").hover(function(){
    $(".fw_cd").toggle();
}) 
   //    快捷导航网站导航下拉菜单
   $(".wz_dh").hover(function(){
    $(".dh_cd").toggle();
}) 
    //  我的购物车导航下拉栏
    // $(".my_shopcar").hover(function(){
    //     $(".J_cart_pop").toggle();
    // }) 
})