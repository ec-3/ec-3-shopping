package com.peaq.ec3.shopp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "snow")
public class SnowflakeProperties {

    // 工作ID
    private long workerId;
    // 数据中心ID
    private long datacenterId;
    private long sequenceBits;

}
