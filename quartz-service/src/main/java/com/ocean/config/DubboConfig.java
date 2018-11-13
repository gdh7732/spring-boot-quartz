package com.ocean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * @author guodahai
 */
@Configuration
public class DubboConfig {
    @Value("${spring.dubbo.application.name}")
    private String dubbo_application_name;

    @Value("${spring.dubbo.registry.address}")
    private String dubbo_registry_address;

    @Value("${spring.dubbo.protocol.name}")
    private String spring_dubbo_protocol_name;

    @Value("${spring.dubbo.protocol.port}")
    private int spring_dubbo_protocol_port;

    /**
     * 由《dubbo:application》转换过来
     **/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger("slf4j");
        applicationConfig.setName(dubbo_application_name);
        return applicationConfig;
    }

    /**
     * 与<dubbo:annotation/>相当.提供方扫描带有@com.alibaba.dubbo.init.annotation.Reference的注解类
     */
    @Bean
    public static AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        // 多个包可使用英文逗号隔开
        annotationBean.setPackage("com.ocean.service");
        return annotationBean;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(spring_dubbo_protocol_name);
        protocolConfig.setPort(spring_dubbo_protocol_port);
        return protocolConfig;

    }

    /**
     * 与<dubbo:registry/>相当
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubbo_registry_address);
        return registryConfig;
    }
}