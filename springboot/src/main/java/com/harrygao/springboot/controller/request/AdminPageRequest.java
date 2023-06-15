package com.harrygao.springboot.controller.request;

import lombok.Data;

@Data
public class AdminPageRequest extends BaseRequest{
    private String username;
    private String phone;
    private String email;
}
