package com.ocean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * dubbo配置
 *
 * @author guodahai
 */
@Configuration
public class DubboConfig {
    @Value("${spring.dubbo.application.name}")
    private String applicationName;

    @Value("${spring.dubbo.registry.address}")
    private String registryAddress;

    @Value("${spring.dubbo.protocol.name}")
    private String protocolName;

    @Value("${spring.dubbo.protocol.port}")
    private int protocolPort;

    /**
     * 由《dubbo:application》转换过来
     **/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger("slf4j");
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;

    }

    /**
     * 与<dubbo:registry/>相当
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddress);
        return registryConfig;
    }
}