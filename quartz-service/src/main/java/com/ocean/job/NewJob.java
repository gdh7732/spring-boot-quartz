package com.ocean.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Job-new
 *
 * @author guodahai
 */
public class NewJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(NewJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.warn("New Job执行时间: " + new Date());
    }
}  