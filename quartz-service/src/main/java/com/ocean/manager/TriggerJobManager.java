package com.ocean.manager;


import com.ocean.ServiceException;
import com.ocean.model.TriggerJobDO;
import com.ocean.request.TriggerRequest;

import java.util.List;
import java.util.Map;

/**
 * 定时任务管理manager
 *
 * @author guodahai
 */
public interface TriggerJobManager {

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
