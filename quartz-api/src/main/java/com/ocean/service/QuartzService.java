package com.ocean.service;

import java.util.Map;

import com.ocean.ServiceException;
import com.ocean.request.TriggerRequest;

/**
 * @author guodahai
 * @version 2018/4/16 下午5:25
 */
public interface QuartzService {

    /**
     * 添加job任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean add(TriggerRequest request) throws ServiceException;

    /**
     * 停止job任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean pause(TriggerRequest request) throws ServiceException;

    /**
     * 复原job任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean resume(TriggerRequest request) throws ServiceException;

    /**
     * 重启job任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean reschedule(TriggerRequest request) throws ServiceException;

    /**
     * 删除job任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean delete(TriggerRequest request) throws ServiceException;

    /**
     * 分页查询定时任务
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ServiceException
     */
    Map getJobAndTriggerDetails(int pageNum, int pageSize) throws ServiceException;
}
