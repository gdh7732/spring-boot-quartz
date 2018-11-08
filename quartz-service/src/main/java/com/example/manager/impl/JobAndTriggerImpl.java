package com.example.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.common.ErrorCodeEnum;
import com.example.common.ServiceException;
import com.example.dao.TriggerMapper;
import com.example.model.TriggerJobDO;
import com.example.model.TriggerRequest;
import com.example.manager.JobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * @author guodahai
 */
@Service
public class JobAndTriggerImpl implements JobAndTriggerService {

    private Logger logger = LoggerFactory.getLogger(TriggerJobDO.class);
    @Resource
    private TriggerMapper triggerMapper;

    @Override
    public Map getJobAndTriggerDetails(int pageNum, int pageSize) throws ServiceException {
        PageHelper.startPage(pageNum, pageSize);
        List<TriggerJobDO> triggerJobDOS = triggerMapper.queryByParam(new TriggerJobDO());
        PageInfo<TriggerJobDO> page = new PageInfo<>(triggerJobDOS);
        Map<String, Object> map = new HashMap<>(2);
        map.put("JobAndTrigger", page);
        map.put("number", page.getTotal());
        return map;
    }

    @Override
    public List<TriggerJobDO> getAll() throws ServiceException {
        return triggerMapper.queryByParam(new TriggerJobDO());
    }

    @Override
    public Boolean create(TriggerRequest request) throws ServiceException {
        TriggerJobDO triggerJobDO = new TriggerJobDO();
        triggerJobDO.setJobClassName(request.getJobClassName());
        triggerJobDO.setJobGroup(request.getJobGroup());
        triggerJobDO.setCronExpression(request.getCronExpression());
        try {
            triggerMapper.insert(triggerJobDO);
        } catch (Exception e) {
            logger.error("插入数据失败");
            throw new ServiceException(ErrorCodeEnum.I01);
        }
        return true;
    }

    @Override
    public TriggerJobDO findOne(TriggerRequest request) throws ServiceException {
        TriggerJobDO triggerJobDO = new TriggerJobDO();
        triggerJobDO.setJobClassName(request.getJobClassName());
        triggerJobDO.setJobGroup(request.getJobGroup());
        List<TriggerJobDO> triggerJobDOS = triggerMapper.queryByParam(triggerJobDO);
        if (CollectionUtils.isEmpty(triggerJobDOS)) {
            return null;
        }
        return triggerJobDOS.get(0);
    }

    @Override
    public Boolean update(TriggerJobDO trigger) throws ServiceException {
        try {
            triggerMapper.update(trigger);
        } catch (Exception e) {
            logger.error("更新数据失败");
            throw new ServiceException(ErrorCodeEnum.U01);
        }
        return true;
    }

}