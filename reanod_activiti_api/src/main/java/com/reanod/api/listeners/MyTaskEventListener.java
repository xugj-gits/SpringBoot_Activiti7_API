package com.reanod.api.listeners;
import org.activiti.api.model.shared.event.RuntimeEvent;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.runtime.events.*;
import org.activiti.api.task.runtime.events.listener.TaskRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * @author xugj<br>
 * @version 1.0<br>
 * @createDate 2019/05/31 14:11 <br>
 * @Description <p> </p>
 */
@Service
public class MyTaskEventListener implements TaskRuntimeEventListener {
    private Logger logger = LoggerFactory.getLogger(MyTaskEventListener.class);

    @Override
    public void onEvent(RuntimeEvent runtimeEvent) {

        if (runtimeEvent instanceof TaskActivatedEvent) {
            logger.info("Do something, task is activated: " + runtimeEvent.toString());
        } else if (runtimeEvent instanceof TaskAssignedEvent) {
            TaskAssignedEvent taskEvent = (TaskAssignedEvent)runtimeEvent;
            Task task = taskEvent.getEntity();
            logger.info("Do something, task is assigned: " + task.toString());
        } else if (runtimeEvent instanceof TaskCancelledEvent) {
            logger.info("Do something, task is cancelled: " + runtimeEvent.toString());
        } else if (runtimeEvent instanceof TaskCompletedEvent) {
            logger.info("Do something, task is completed: " + runtimeEvent.toString());
        } else if (runtimeEvent instanceof TaskCreatedEvent) {
            logger.info("Do something, task is created: " + runtimeEvent.toString());
        } else if (runtimeEvent instanceof TaskSuspendedEvent) {
            logger.info("Do something, task is suspended: " + runtimeEvent.toString());
        } else {
            logger.info("Unknown event: " + runtimeEvent.toString());
        }

    }
}
