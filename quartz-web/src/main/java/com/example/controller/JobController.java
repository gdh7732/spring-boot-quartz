package com.example.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

import com.example.common.ControllerExecutor;
import com.example.common.ResponseResult;
import com.example.common.ServiceException;
import com.example.model.TriggerRequest;
import com.example.manager.JobAndTriggerService;
import com.example.manager.SchedulerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 定时任务控制层
 *
 * @author guodahai
 */
@RestController
@RequestMapping(value = "/job")
@Api("JobController相关api")
public class JobController {
    @Resource
    private JobAndTriggerService triggerService;
    @Resource
    private SchedulerService schedulerService;

    @GetMapping
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }

    @ApiOperation("添加定时任务")
    @PostMapping(value = "/add")
    public ResponseResult<Boolean> add(TriggerRequest request) throws ServiceException {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return schedulerService.add(request);
            }
        }.execute(request);

    }


    @PostMapping(value = "/pause")
    public ResponseResult<Boolean> pause(TriggerRequest request) throws ServiceException {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return schedulerService.pause(request);
            }
        }.execute(request);
    }


    @PostMapping(value = "/resume")
    public ResponseResult<Boolean> resume(TriggerRequest request) throws ServiceException {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return schedulerService.resume(request);
            }
        }.execute(request);
    }


    @PostMapping(value = "/reschedule")
    public ResponseResult<Boolean> reschedule(TriggerRequest request) throws Exception {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return schedulerService.reschedule(request);
            }
        }.execute(request);
    }


    @PostMapping(value = "/delete")
    public ResponseResult<Boolean> delete(TriggerRequest request) throws Exception {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return schedulerService.delete(request);
            }
        }.execute(request);
    }


    @GetMapping(value = "/query")
    public ResponseResult<Map> query(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) throws Exception {
        return new ControllerExecutor<Map, Integer>(pageNum, pageSize) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Map executeService() throws ServiceException {
                return triggerService.getJobAndTriggerDetails(pageNum, pageSize);
            }
        }.execute(pageNum, pageSize);

    }


}
