package com.ocean.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author guodahai
 */
public class TriggerJobDTO implements Serializable {

    private static final long serialVersionUID = -1691527400792106382L;
    private Long id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务所在组
     */
    private String jobGroup;
    /**
     * 任务类名
     */
    private String jobClassName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器所在组
     */
    private String triggerGroup;
    /**
     * 是否删除0：否，1：是
     */
    private BigInteger isDelete;
    /**
     * 是否暂停0：否，1：是
     */
    private BigInteger isPause;
    /**
     * 表达式
     */
    private String cronExpression;
    /**
     * 时区
     */
    private String timeZoneId;

    public TriggerJobDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public BigInteger getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(BigInteger isDelete) {
        this.isDelete = isDelete;
    }

    public BigInteger getIsPause() {
        return isPause;
    }

    public void setIsPause(BigInteger isPause) {
        this.isPause = isPause;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
