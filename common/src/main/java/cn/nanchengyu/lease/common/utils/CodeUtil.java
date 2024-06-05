package cn.nanchengyu.lease.common.utils;

import java.util.Random;

/**
 * ClassName: CodeUtil
 * Package: cn.nanchengyu.lease.common.utils
 * Description:
 *
 * @Author 南城余
 * @Create 2024/6/5 20:53
 * @Version 1.0
 */
public class CodeUtil {
    public static String getRandomCode(Integer length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            builder.append(num);
        }
        return builder.toString();
    }
}
