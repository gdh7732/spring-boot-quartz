package com.ocean.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Job-hello
 *
 * @author guodahai
 */
public class HelloJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.warn("Hello Job执行时间: " + new Date());
    }
}  
