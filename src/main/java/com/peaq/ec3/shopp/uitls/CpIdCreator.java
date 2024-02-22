package com.peaq.ec3.shopp.uitls;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CpIdCreator {

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 大写的I 同小写的l 不易区分，不参与
     */
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    public String flowId() {
        return String.valueOf(snowflakeIdWorker.nextId());
    }

    /**
     * 支付订单号(唯一) 格式：年份后2位(yyyy) + 1年中的第?天数3位+ 一代后2位 + 雪花算法后11位,数字一共18位
     *
     * @return
     */
    public String createOrderId() {
        String flowId = String.valueOf(snowflakeIdWorker.nextId());
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        int days = calendar.get(Calendar.DAY_OF_YEAR);
        String yyYear = getYY(calendar.getTime());
        String daysOfYear = numberToStr(3, days);
        String suffix = flowId.substring(flowId.length() - 11);
        sb.append(yyYear).append(daysOfYear).append(suffix);
        return sb.toString();
    }

    private String getYY(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        return sdf.format(date);
    }

    /**
     * @param digits 指定位数
     * @param number 数字
     * @return
     */
    private String numberToStr(int digits, long number) {
        String daysNumber = String.valueOf(number);
        if (daysNumber.length() < digits) {
            daysNumber = String.format("%0" + digits + "d", number);
        } else if (daysNumber.length() > digits) {
            daysNumber = daysNumber.substring(daysNumber.length() - digits);
        }
        return daysNumber;
    }

    /**
     * 软件序列号sn的生成：厂商编号确定第1位 + 雪花算法的后10随机数
     *
     * @param manufacturerNum 乐刷供应商编号
     * @return
     */
    public String productNum(String manufacturerNum) {
        int len = chars.length;
        StringBuffer sb = new StringBuffer();
        String flowId = flowId();
        int hash = manufacturerNum.hashCode();
        sb.append(chars[(hash < 0 ? -hash : hash) % len])
                .append(flowId.substring(flowId.length() - 10));
        return sb.toString();
    }

}
