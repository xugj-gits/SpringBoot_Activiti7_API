package com.reanod.api.controller;


import com.reanod.api.utils.RestMessgae;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xugj<br>
 * @version 1.0<br>
 * @createDate 2019/05/31 8:30 <br>
 * @Description <p> 列出流程定义 </p>
 */

@RestController
@Api(tags = "列出流程定义")
public class ProcessDefinitionsController {
    private Logger logger = LoggerFactory.getLogger(ProcessDefinitionsController.class);

    private final ProcessRuntime processRuntime;

    public ProcessDefinitionsController(ProcessRuntime processRuntime) {
        this.processRuntime = processRuntime;
    }

    @RequestMapping(value = "/process-definitions/{startIndex}/{maxItems}", method = RequestMethod.GET)
    @ApiOperation(value = "列出流程定义", notes = "（列出流程定义（支持分页，但是好像不太管事￣□￣｜｜）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startIndex", value = "起始数", dataType = "int", paramType = "path", example = "0"),
            @ApiImplicitParam(name = "maxItems", value = "最大条数", dataType = "int", paramType = "path", example = "10")
    })
    public RestMessgae getProcessDefinitions(@PathVariable("startIndex") Integer startIndex,
                                             @PathVariable("maxItems") Integer maxItems) {

        RestMessgae restMessgae = new RestMessgae();
        Page<ProcessDefinition> processDefinitionPage = null;
        try {
            processDefinitionPage = processRuntime.processDefinitions(Pageable.of(startIndex, maxItems));
        } catch (Exception e) {
            e.printStackTrace();
            restMessgae = RestMessgae.fail("查询失败", e.getMessage());
        }

        if (processDefinitionPage != null) {
            restMessgae = RestMessgae.success("查询成功", processDefinitionPage.getContent());
        }

        return restMessgae;
    }
}
