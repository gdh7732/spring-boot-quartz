package com.ocean.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author guodahai
 */
public interface BaseJob extends Job {
    public void execute(JobExecutionContext context) throws JobExecutionException;
}

