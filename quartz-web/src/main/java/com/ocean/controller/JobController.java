package com.ocean.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ocean.ControllerExecutor;
import com.ocean.ResponseResult;
import com.ocean.ServiceException;
import com.ocean.request.TriggerRequest;
import com.ocean.service.QuartzService;

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
    @Reference
    private QuartzService quartzService;

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
                return quartzService.add(request);
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
                return quartzService.pause(request);
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
                return quartzService.resume(request);
            }
        }.execute(request);
    }


    @PostMapping(value = "/reschedule")
    public ResponseResult<Boolean> reschedule(TriggerRequest request) {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return quartzService.reschedule(request);
            }
        }.execute(request);
    }


    @PostMapping(value = "/delete")
    public ResponseResult<Boolean> delete(TriggerRequest request) {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Boolean executeService() throws ServiceException {
                return quartzService.delete(request);
            }
        }.execute(request);
    }


    @GetMapping(value = "/query")
    public ResponseResult<Map> query(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return new ControllerExecutor<Map, Integer>(pageNum, pageSize) {
            @Override
            public void checkParam() throws ServiceException {
            }

            @Override
            public Map executeService() throws ServiceException {
                return quartzService.getJobAndTriggerDetails(pageNum, pageSize);
            }
        }.execute(pageNum, pageSize);

    }

}
