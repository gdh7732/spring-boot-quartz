package com.ocean.manager;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.ocean.QuartzServiceApplication;
import com.ocean.model.TriggerJobDO;
import com.ocean.request.TriggerRequest;

/**
 * @author guodahai
 * @version 2018/11/19 17:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TriggerJobManagerTest {

    @Resource
    private TriggerJobManager triggerJobManager;

    @Test
    public void getJobAndTriggerDetails() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void findOne() {
        TriggerRequest request = new TriggerRequest();
        request.setJobGroup("test");
        TriggerJobDO triggerJobDO = triggerJobManager.findOne(request);
        System.out.println(triggerJobDO);
    }

    @Test
    public void update() {
    }
}
