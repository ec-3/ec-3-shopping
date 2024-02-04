package com.peaq.ec3.shopp.common;


public class ReturnCode {
    /**
     * 返回码：成功
     */
    public static String CODE_0 = "0";
    /**
     * 返回码：失败
     */
    public static String CODE_9 = "9";
    /**
     * 返回码：超时
     */
    public static String CODE_5 = "5";
    /**
     * 返回码：sessionId错误
     */
    public static String CODE_99 = "99";
    /**
     * 返回描述
     */
    public static String DESC_0 = "成功";
    /**
     * 返回描述
     */
    public static String DESC_9 = "失败";
    /**
     * 返回描述
     */
    public static String DESC_5 = "超时";
    /**
     * 返回描述
     */
    public static String DESC_99 = "sessionId错误";

    /**
     * session类错误
     */
    public static String ERROR_SESSION="7";

    /**
     * 账号冻结
     */
    public static String FREEZE_ACCOUNT = "8";

    /**
     * 错误码--账号冻结
     */
    public static Integer CODE_FREEZE_ACCOUNT = 8;

    /**
     * 错误码--认证次数超限
     */
    public static Integer CODE_VERIFY_TIMES_OUT = 6;

    /**
     * 错误码--重复注册
     */
    public static Integer CODE_REPEAT_REGISTER = 5;

    /**
     * 返回码：机具数不足100台单位
     */
    public static String CODE_101 = "101";

    /**
     * 返回描述：机具数不足100
     */
    public static String DESC_101 = "机具数不足100台单位";

    /**
     * 返回码：代理商 未挂靠富匙机构;无法修改新商户默认活动;
     */
    public static String CODE_102 = "102";
    /**
     * 返回描述：代理商 未挂靠富匙机构;无法修改新商户默认活动;
     */
    public static String DESC_102 = "代理商 未挂靠富匙机构;无法修改新商户默认活动;";

    /**
     * 返回码：代理商 未配置26标签;无法修改新商户默认活动;
     */
    public static String CODE_103 = "103";
    /**
     * 返回描述：代理商 未配置26标签;无法修改新商户默认活动;
     */
    public static String DESC_103 = "代理商 未配置26标签;无法修改新商户默认活动;";

    /**
     * 返回码：代理商 未挂靠富匙机构;未配置26标签;无法修改新商户默认活动;
     */
    public static String CODE_104 = "104";
    /**
     * 返回描述：代理商 未挂靠富匙机构;未配置26标签;无法修改新商户默认活动;
     */
    public static String DESC_104 = "代理商 未挂靠富匙机构;未配置26标签;无法修改新商户默认活动;";
}
