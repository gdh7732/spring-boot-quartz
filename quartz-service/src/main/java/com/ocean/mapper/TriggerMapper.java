package com.ocean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ocean.model.TriggerJobDO;

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
     * @param id 主键
     * @return 定时任务DO
     */
    TriggerJobDO queryById(Integer id);

    /**
     * 动态查询
     *
     * @param triggerJobDO 查询条件
     * @return 定时任务DO列表
     */
    List<TriggerJobDO> queryByParam(TriggerJobDO triggerJobDO);

    /**
     * 新增
     *
     * @param triggerJobDO 定时任务DO
     * @return 新增数量
     */
    int insert(TriggerJobDO triggerJobDO);

    /**
     * 更新
     *
     * @param triggerJobDO 定时任务DO
     * @return 更新数量
     */
    int update(TriggerJobDO triggerJobDO);

}
