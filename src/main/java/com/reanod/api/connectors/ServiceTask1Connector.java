package com.reanod.api.connectors;

import org.activiti.api.process.model.IntegrationContext;
import org.activiti.api.process.runtime.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author xugj<br>
 * @version 1.0<br>
 * @createDate 2019/05/31 14:06 <br>
 * @Description <p> 实现服务任务Spring Bean </p>
 */
@Service(value = "serviceTask1Impl")
public class ServiceTask1Connector implements Connector {
    private Logger logger = LoggerFactory.getLogger(ServiceTask1Connector.class);

    public IntegrationContext execute(IntegrationContext integrationContext) {
        logger.info("Some service task logic... [processInstanceId=" + integrationContext.getProcessInstanceId() + "]");

        return integrationContext;
    }

    @Override
    public IntegrationContext apply(IntegrationContext integrationContext) {
        return null;
    }

    @Override
    public <V> Function<V, IntegrationContext> compose(Function<? super V, ? extends IntegrationContext> before) {
        return null;
    }

    @Override
    public <V> Function<IntegrationContext, V> andThen(Function<? super IntegrationContext, ? extends V> after) {
        return null;
    }
}
