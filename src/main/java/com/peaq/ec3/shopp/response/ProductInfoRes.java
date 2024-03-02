package com.peaq.ec3.shopp.response;

import com.peaq.ec3.shopp.model.ProductInfo;
import com.peaq.ec3.shopp.model.ProductPic;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ProductInfoRes<T> extends PageResponse<T>{

    private Map<Long, List<ProductPic>> keyPics;
    private Map<Long, List<ProductInfo>> productMap;
}
