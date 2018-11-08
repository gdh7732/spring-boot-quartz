package com.example.service;


import com.example.common.ServiceException;
import com.example.model.TriggerJobDO;
import com.example.model.TriggerRequest;

import java.util.List;
import java.util.Map;

/**
 * @author guodahai
 */
public interface JobAndTriggerService {

    /**
     * 分页查询定时任务
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ServiceException
     */
    Map getJobAndTriggerDetails(int pageNum, int pageSize) throws ServiceException;

    /**
     * 查询所有定时任务
     *
     * @return
     * @throws ServiceException
     */
    List<TriggerJobDO> getAll() throws ServiceException;

    /**
     * 添加定时任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Boolean create(TriggerRequest request) throws ServiceException;

    /**
     * 查询定时任务
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    TriggerJobDO findOne(TriggerRequest request) throws ServiceException;

    /**
     * 修改定时任务
     *
     * @param trigger
     * @return
     * @throws ServiceException
     */
    Boolean update(TriggerJobDO trigger) throws ServiceException;

}
