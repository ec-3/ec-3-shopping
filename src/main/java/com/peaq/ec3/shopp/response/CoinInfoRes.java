package com.peaq.ec3.shopp.response;

import com.peaq.ec3.shopp.model.ChainNetwork;
import com.peaq.ec3.shopp.model.CoinInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CoinInfoRes {

    private List<CoinInfo> coins;
    private List<ChainNetwork> chainNetworks;
    private Map<Long,List<ChainNetwork>> coinNet;

}
