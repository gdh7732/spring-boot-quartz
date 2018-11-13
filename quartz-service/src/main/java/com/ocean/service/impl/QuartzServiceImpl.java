package com.ocean.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.ocean.manager.SchedulerManager;
import com.ocean.manager.TriggerJobManager;
import com.ocean.ServiceException;
import com.ocean.request.TriggerRequest;
import com.ocean.service.QuartzService;

/**
 * 定时任务Service实现
 *
 * @author guodahai
 * @version 2018/11/8 17:15
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Resource
    private SchedulerManager schedulerManager;
    @Resource
    private TriggerJobManager triggerJobManager;

    @Override
    public Boolean add(TriggerRequest request) throws ServiceException {
        return schedulerManager.add(request);
    }

    @Override
    public Boolean pause(TriggerRequest request) throws ServiceException {
        return schedulerManager.pause(request);
    }

    @Override
    public Boolean resume(TriggerRequest request) throws ServiceException {
        return schedulerManager.resume(request);
    }

    @Override
    public Boolean reschedule(TriggerRequest request) throws ServiceException {
        return schedulerManager.reschedule(request);
    }

    @Override
    public Boolean delete(TriggerRequest request) throws ServiceException {
        return schedulerManager.delete(request);
    }

    @Override
    public Map getJobAndTriggerDetails(int pageNum, int pageSize) throws ServiceException {
        return triggerJobManager.getJobAndTriggerDetails(pageNum, pageSize);
    }
}
