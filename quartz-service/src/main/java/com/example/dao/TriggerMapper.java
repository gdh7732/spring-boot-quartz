package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.TriggerJobDO;

/**
 * 定时任务mapper
 *
 * @author guodahai
 */
@Mapper
public interface TriggerMapper {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    TriggerJobDO queryById(Integer id);

    /**
     * 动态查询
     *
     * @param triggerJobDO
     * @return
     */
    List<TriggerJobDO> queryByParam(TriggerJobDO triggerJobDO);

    /**
     * 新增
     *
     * @param triggerJobDO
     * @return
     */
    int insert(TriggerJobDO triggerJobDO);

    /**
     * 更新
     *
     * @param triggerJobDO
     * @return
     */
    int update(TriggerJobDO triggerJobDO);

}
