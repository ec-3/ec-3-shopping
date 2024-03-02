package com.peaq.ec3.shopp.controller;

import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.MerchantChainPayMapper;
import com.peaq.ec3.shopp.model.MerchantChainPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantChainPayMapper merchantChainPayMapper;

    @PostMapping("chainPayList")
    public Result<List<MerchantChainPay>> merchantChainPay(){
        return Result.returnSuccess(merchantChainPayMapper.list());
    }
}
