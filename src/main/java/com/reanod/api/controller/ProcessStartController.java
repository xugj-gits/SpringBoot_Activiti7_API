package com.reanod.api.controller;

import com.reanod.api.utils.RestMessgae;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author xugj<br>
 * @version 1.0<br>
 * @createDate 2019/05/31 10:58 <br>
 * @Description <p> 启动流程实例 </p>
 */
@RestController
@Api(tags = "启动流程实例")
public class ProcessStartController {
    private Logger logger = LoggerFactory.getLogger(ProcessStartController.class);

    private final ProcessRuntime processRuntime;

    public ProcessStartController(ProcessRuntime processRuntime) {
        this.processRuntime = processRuntime;
    }

    @GetMapping("/start-process/{processDefinitionKey}")
    @ApiOperation(value = "启动流程实例", notes = "启动流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionKey", value = "定义流程KEY", dataType = "String", paramType = "path", example = "SampleProcess")
    })
    public RestMessgae startProcess(
            @PathVariable("processDefinitionKey") String processDefinitionKey) {

        RestMessgae restMessgae = new RestMessgae();
        ProcessInstance processInstance = null;
        try {
            processInstance = processRuntime.start(ProcessPayloadBuilder
                    .start()
                    .withProcessDefinitionKey(processDefinitionKey)
                    .withVariable("someProcessVar", "someProcVarValue")
                    .withVariable("assignee", "other")
                    .withVariable("msg", "重要情况")
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            restMessgae = RestMessgae.fail("启动失败", e.getMessage());
        }

        if (processInstance != null) {
            restMessgae = RestMessgae.success("启动成功", processInstance);
        }

        return restMessgae;
    }
}
