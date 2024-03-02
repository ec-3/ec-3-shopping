package com.peaq.ec3.shopp.response;

import com.peaq.ec3.shopp.model.ProductInfo;
import com.peaq.ec3.shopp.model.ProductPic;
import lombok.Data;

import java.util.List;

@Data
public class ProductRes {
    private ProductInfo product;
    private String masterUrl;
    private List<ProductPic> pics;
}
