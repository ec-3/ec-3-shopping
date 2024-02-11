package com.peaq.ec3.shopp.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.ProductInfoMapper;
import com.peaq.ec3.shopp.mapper.ProductPicMapper;
import com.peaq.ec3.shopp.model.ProductInfo;
import com.peaq.ec3.shopp.model.ProductPic;
import com.peaq.ec3.shopp.request.ProductListReq;
import com.peaq.ec3.shopp.response.PageResponse;
import com.peaq.ec3.shopp.response.ProductInfoRes;
import com.peaq.ec3.shopp.response.ProductRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductInfoController {

    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    ProductPicMapper productPicMapper;

    @GetMapping("/list")
    public Result<ProductInfoRes<ProductRes>> list(@RequestBody ProductListReq request) {
        Page<ProductInfo> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())
                .doSelectPage(() -> productInfoMapper.getProductInfoList(request));
        List<ProductInfo> info = page.getResult();
        List<Long> ids = info.stream().map(ProductInfo::getId).collect(Collectors.toList());
        ProductInfoRes<ProductRes> result = new ProductInfoRes<>();
        List<ProductRes> productRes = new ArrayList<>(info.size());
        List<ProductPic> pics = productPicMapper.getPicByPIds(ids);
        Map<Long, List<ProductPic>> keyPics = pics.stream().collect(Collectors.groupingBy(ProductPic::getProductId));
        keyPics.forEach((key,value)->{
           value.sort((p1,p2)->p1.getPicOrder().compareTo(p2.getPicOrder()));
            ProductRes res = new ProductRes();
            res.setPics(value);
        });
        result.setKeyPics(keyPics);
        result.setRecords(productRes);
        result.setTotal(page.getTotal());
        return Result.returnSuccess(result);
    }
}