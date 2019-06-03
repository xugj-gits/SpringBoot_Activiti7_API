package com.reanod.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskAdminRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xugj<br>
 * @version 1.0<br>
 * @createDate 2019/05/31 11:29 <br>
 * @Description <p> 用户任务 </p>
 */
@RestController
@Api(tags = "用户任务")
public class TaskManagementController {
    private Logger logger = LoggerFactory.getLogger(TaskManagementController.class);

    private final TaskRuntime taskRuntime;

    private final TaskAdminRuntime taskAdminRuntime;

    public TaskManagementController(TaskRuntime taskRuntime, TaskAdminRuntime taskAdminRuntime) {
        this.taskRuntime = taskRuntime;
        this.taskAdminRuntime = taskAdminRuntime;
    }

    @GetMapping("/my-tasks")
    @ApiOperation(value = "列出可用的用户任务", notes = "列出可用的用户任务")
    public List<Task> getMyTasks() {
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        logger.info("> My Available Tasks: " + tasks.getTotalItems());

        for (Task task : tasks.getContent()) {
            logger.info("\t> My User Task: " + task);
        }

        return tasks.getContent();
    }

    @GetMapping("/all-tasks")
    @ApiOperation(value = "查看在所有活动流程实例中分配的所有任务", notes = "查看在所有活动流程实例中分配的所有任务")
    public List<Task> getAllTasks() {
        Page<Task> tasks = taskAdminRuntime.tasks(Pageable.of(0, 10));
        logger.info("> All Available Tasks: " + tasks.getTotalItems());

        for (Task task : tasks.getContent()) {
            logger.info("\t> User Task: " + task);
        }

        return tasks.getContent();
    }

    @GetMapping("/complete-task/{taskId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", dataType = "String", paramType = "path", example = "")
    })
    @ApiOperation(value = "根据taskId提交完成任务", notes = "根据taskId提交完成任务")
    public String completeTask(@PathVariable(value="taskId") String taskId) {
        taskRuntime.complete(TaskPayloadBuilder.complete()
                .withTaskId(taskId).build());
        logger.info(">>> Completed Task: " + taskId);

        return "Completed Task: " + taskId;
    }
}
