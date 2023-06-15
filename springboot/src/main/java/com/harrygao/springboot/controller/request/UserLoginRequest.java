package com.harrygao.springboot.controller.request;

/**
 * @program: QIn-UML - Copy
 * @author: HarryGao
 * @create: 2023-06-13 15:10
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserLoginRequest {
    private String name;
    private String password;
}

