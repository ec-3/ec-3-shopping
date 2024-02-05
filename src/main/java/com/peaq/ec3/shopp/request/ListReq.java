package com.peaq.ec3.shopp.request;

import lombok.Data;

@Data
public class ListReq extends PageBase {

    private String userId;
    private String orderId;
}
