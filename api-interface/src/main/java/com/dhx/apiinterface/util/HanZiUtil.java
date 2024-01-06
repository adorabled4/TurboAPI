package com.dhx.apiinterface.util;

/**
 * @author adorabled4
 * @className HanZiUtil
 * @date : 2024/01/06/ 18:36
 **/
public class HanZiUtil {

    /**
     * 返回一个汉字
     *
     * @param
     * @return
     */
    public static char getRandomHanZi() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }

    /**
     * 获取多个汉字中间有空格
     *
     * @param size 汉字个数
     * @return
     */
    public static String getRandomHanZi(int size) {
        if (size <= 0) {
            size = 1;
        }
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < size; i++) {
            stringBuffer.append(getRandomHanZi() + " ");
        }
        return stringBuffer.toString();
    }

    /**
     * 获取多个汉字中间无空格
     *
     * @param size 汉字个数
     * @return
     */
    public static String getRandomHanZiNoSpace(int size) {

        if (size <= 0) {
            size = 1;
        }
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < size; i++) {
            stringBuffer.append(getRandomHanZi());
        }
        return stringBuffer.toString();
    }

}
