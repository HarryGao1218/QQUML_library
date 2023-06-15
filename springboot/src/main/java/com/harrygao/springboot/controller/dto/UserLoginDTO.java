package com.harrygao.springboot.controller.dto;
import lombok.Data;
/**
 * @program: QIn-UML - Copy
 * @author: HarryGao
 * @create: 2023-06-13 14:24
 */


@Data
public class UserLoginDTO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private Integer account;
    private String token;
}
