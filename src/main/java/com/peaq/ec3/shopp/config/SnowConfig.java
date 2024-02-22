package com.peaq.ec3.shopp.config;

import com.peaq.ec3.shopp.uitls.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableConfigurationProperties({SnowflakeProperties.class})
public class SnowConfig implements EnvironmentAware {

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    private Environment environment;

    public SnowConfig() {
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(snowflakeProperties.getWorkerId(),snowflakeProperties.getDatacenterId());
    }
}
