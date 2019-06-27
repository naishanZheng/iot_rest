package com.iot.base.utils;

import java.util.Random;

/**
 * @author zhengnaishan
 * @date 2019/3/5 0005
 * @describe :生成id的工具
 */
public class IDUtils {

    /**
     * 生成id
     */
    public static String getRandomId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上四位随机数
        Random random = new Random();
        int end2 = random.nextInt(999999);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return String.valueOf(id);
    }
}
