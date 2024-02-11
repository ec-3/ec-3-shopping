package com.peaq.ec3.shopp.response;

import com.peaq.ec3.shopp.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRes {

    private UserInfo user;
    private String token;



}
