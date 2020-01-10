package com.honeykee.config.client.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-26 14:39
 * @since JDK 1.8
 */

@Slf4j
@Component
public class TaskListener implements TaskExecutionListener {
    @Override
    public void onTaskStartup( TaskExecution taskExecution ) {

        log.info( "Task StartUp------" );
    }

    @Override
    public void onTaskEnd( TaskExecution taskExecution ) {
        log.info( "Task End------" );

    }

    @Override
    public void onTaskFailed( TaskExecution taskExecution, Throwable throwable ) {
        log.info( "Task Failed------" );

    }
}
