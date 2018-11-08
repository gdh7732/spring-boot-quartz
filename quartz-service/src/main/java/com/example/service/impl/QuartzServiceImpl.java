package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.service.QuartzService;

/**
 * @author guodahai
 * @version 2018/11/8 17:15
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Override
    public String queryDemo() {
        return "demo";
    }
}
