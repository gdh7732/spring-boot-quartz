package com.ocean.config;

import com.ocean.model.TriggerJobDO;
import com.ocean.manager.TriggerJobManager;

import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

/**
 * 定时任务配置
 *
 * @author guodahai
 */
@Configuration
public class SchedulerConfig {

    @Resource
    private TriggerJobManager triggerService;

    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws Exception {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        startHelloJob(scheduler);
        return scheduler;
    }

    private void startHelloJob(Scheduler scheduler) throws Exception {
        List<TriggerJobDO> all = triggerService.getAll();
        // 启动调度器
        scheduler.start();
        for (TriggerJobDO triggerJobDO : all) {
            //构建job信息
            Job baseJob = getClass(triggerJobDO.getJobClassName());
            JobDetail jobDetail = JobBuilder.newJob(baseJob.getClass()).withIdentity(triggerJobDO.getJobClassName(), triggerJobDO.getJobGroup()).build();

            //表达式调度构建器(即任务执行的时间)
            String cronExpression = triggerJobDO.getCronExpression();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerJobDO.getJobClassName(), triggerJobDO.getTriggerGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    private static Job getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (Job) clazz.newInstance();
    }

}
