package com.peaq.ec3.shopp.controller;

import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.AddressGlobalMapper;
import com.peaq.ec3.shopp.model.AddressGlobal;
import com.peaq.ec3.shopp.request.ListReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/addressGlobal")
@RestController
public class AddressGlobalController {

    @Autowired
    AddressGlobalMapper addressGlobalMapper;

    @PostMapping("/list")
    public Result<List<AddressGlobal>> list(@RequestBody ListReq listReq) {
        return Result.returnSuccess(addressGlobalMapper.list(listReq.getUserId()));
    }

    @PostMapping("/save")
    public Result save(@RequestBody AddressGlobal address) {
        return Result.returnSuccess(addressGlobalMapper.insertSelective(address));
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody AddressGlobal address) {
        return Result.returnSuccess(addressGlobalMapper.updateByPrimaryKeySelective(address));
    }

}
