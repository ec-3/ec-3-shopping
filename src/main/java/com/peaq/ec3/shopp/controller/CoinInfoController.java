package com.peaq.ec3.shopp.controller;

import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.ChainNetworkMapper;
import com.peaq.ec3.shopp.mapper.CoinInfoMapper;
import com.peaq.ec3.shopp.mapper.CoinNetworkMapper;
import com.peaq.ec3.shopp.model.ChainNetwork;
import com.peaq.ec3.shopp.model.CoinInfo;
import com.peaq.ec3.shopp.model.CoinNetwork;
import com.peaq.ec3.shopp.response.CoinInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/coin")
@RestController
public class CoinInfoController {

    @Autowired
    CoinInfoMapper coinInfoMapper;
    @Autowired
    CoinNetworkMapper coinNetworkMapper;
    @Autowired
    ChainNetworkMapper chainNetworkMapper;

    @PostMapping("/coinInfo")
    public Result<CoinInfoRes> getCoinInfo() {
        CoinInfoRes coinInfoRes = new CoinInfoRes();
        List<CoinInfo> coins = coinInfoMapper.list();
        List<ChainNetwork> chainNetworks = chainNetworkMapper.list();
        coinInfoRes.setCoins(coins);
        coinInfoRes.setChainNetworks(chainNetworks);
        if (!CollectionUtils.isEmpty(coins)) {
            List<Long> ids = coins.stream().map(CoinInfo::getId).collect(Collectors.toList());
            List<CoinNetwork> coinNetworks = coinNetworkMapper.batchByIds(ids);
            if (!CollectionUtils.isEmpty(chainNetworks)) {
                Map<Long, List<ChainNetwork>> cn =
                        chainNetworks.stream().collect(Collectors.groupingBy(ChainNetwork::getId));
                Map<Long, List<ChainNetwork>> coinNet = new HashMap<>();
                for (CoinNetwork coinNetwork : coinNetworks) {
                    if (!coinNet.containsKey(coinNetwork.getCoinId())) {
                        coinNet.put(coinNetwork.getCoinId(), cn.get(coinNetwork.getNetId()));
                    } else {
                        List<ChainNetwork> cns = cn.get(coinNetwork.getNetId());
                        if (!CollectionUtils.isEmpty(cns)) {
                            cns.addAll(coinNet.get(coinNetwork.getCoinId()));
                            coinNet.put(coinNetwork.getCoinId(), cns);
                        }
                    }
                }
                coinInfoRes.setCoinNet(coinNet);
            }
        }
        return Result.returnSuccess(coinInfoRes);
    }

}
