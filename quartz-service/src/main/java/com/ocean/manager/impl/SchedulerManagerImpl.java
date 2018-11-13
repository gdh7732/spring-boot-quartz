package com.ocean.manager.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ocean.manager.SchedulerManager;
import com.ocean.manager.TriggerJobManager;
import com.ocean.model.TriggerJobDO;
import com.ocean.ErrorCodeEnum;
import com.ocean.ServiceException;
import com.ocean.request.TriggerRequest;

/**
 * @author guodahai
 * @version 2018/4/16 下午5:25
 */
@Service
public class SchedulerManagerImpl implements SchedulerManager {

    private Logger logger = LoggerFactory.getLogger(SchedulerManagerImpl.class);

    @Resource
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Resource
    private TriggerJobManager triggerJobManager;


    @Override
    public Boolean add(TriggerRequest request) throws ServiceException {
        String jobClassName = request.getJobClassName();
        String jobGroup = request.getJobGroup();
        String cronExpression = request.getCronExpression();
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroup).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroup)
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败,trigger:{}", request.toString());
            throw new ServiceException(ErrorCodeEnum.QUA01);
        }
        return triggerJobManager.create(request);
    }

    @Override
    public Boolean pause(TriggerRequest request) throws ServiceException {
        String jobClassName = request.getJobClassName();
        String jobGroup = request.getJobGroup();
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroup));
        } catch (SchedulerException e) {
            logger.error("暂停定时任务失败,trigger:{}", request.toString());
            throw new ServiceException(ErrorCodeEnum.QUA01);
        }
        TriggerJobDO trigger = triggerJobManager.findOne(request);
        trigger.setIsPause(BigInteger.ONE);
        return triggerJobManager.update(trigger);
    }

    @Override
    public Boolean resume(TriggerRequest request) throws ServiceException {
        String jobClassName = request.getJobClassName();
        String jobGroup = request.getJobGroup();
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroup));
        } catch (SchedulerException e) {
            logger.error("恢复定时任务失败,trigger:{}", request.toString());
            throw new ServiceException(ErrorCodeEnum.QUA01);
        }
        TriggerJobDO trigger = triggerJobManager.findOne(request);
        trigger.setIsPause(BigInteger.ZERO);
        return triggerJobManager.update(trigger);
    }

    @Override
    public Boolean reschedule(TriggerRequest request) throws ServiceException {
        String jobClassName = request.getJobClassName();
        String jobGroup = request.getJobGroup();
        String cronExpression = request.getCronExpression();
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroup);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            logger.error("更新定时任务失败,trigger:{}", request.toString());
            throw new ServiceException(ErrorCodeEnum.QUA01);
        }
        TriggerJobDO trigger = triggerJobManager.findOne(request);
        BeanUtils.copyProperties(request, trigger);
        return triggerJobManager.update(trigger);
    }

    @Override
    public Boolean delete(TriggerRequest request) throws ServiceException {
        String jobClassName = request.getJobClassName();
        String jobGroup = request.getJobGroup();
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroup));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroup));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroup));
        } catch (SchedulerException e) {
            logger.error("删除定时任务失败,trigger:{}", request.toString());
            throw new ServiceException(ErrorCodeEnum.QUA01);
        }
        TriggerJobDO trigger = triggerJobManager.findOne(request);
        trigger.setIsDelete(BigInteger.ONE);
        return triggerJobManager.update(trigger);
    }

    private static BaseJob getClass(String classname) throws ServiceException {
        try {
            Class<?> clazz = Class.forName(classname);
            return (BaseJob) clazz.newInstance();
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.P99);
        }
    }
}
