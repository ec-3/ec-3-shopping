package com.peaq.ec3.shopp.request;

import lombok.Data;

@Data
public class UserLogin {

    private String email;
    private String username;
    private String password;

}
