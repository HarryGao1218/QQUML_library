package com.harrygao.springboot;
import cn.hutool.crypto.SecureUtil;
/**
 * @program: QIn-UML - Copy
 * @author: HarryGao
 * @create: 2023-06-13 14:54
 */
public class demo {
    public static void main(String[] args) {
        String password = "123123";
        String PASS_SALT = "harry";
        System.out.println(SecureUtil.md5(password+PASS_SALT));
    }
}
