package com.peaq.ec3.shopp.request;

import lombok.Data;

@Data
public class PageBase {

    private int pageNo = 1;
    private int pageSize = 10;
}
