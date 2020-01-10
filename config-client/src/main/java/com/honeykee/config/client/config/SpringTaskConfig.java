package com.honeykee.config.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-26 14:07
 * @since JDK 1.8
 */
@Configuration
@Slf4j
public class SpringTaskConfig  {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DefaultTaskConfigurer getTaskConfigurer(DataSource dataSource){
        return new DefaultTaskConfigurer( dataSource );
    }

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("job1step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("Tasklet has run");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step2() {
        return this.stepBuilderFactory.get("job1step2").<String, String>chunk(3)
                .reader(new ListItemReader<>( Arrays.asList("7", "2", "3", "10", "5", "6")))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        log.info("Processing of chunks");
                        return String.valueOf(Integer.parseInt(item) * -1);
                    }
                }).writer(new ItemWriter<String>() {
                    @Override
                    public void write( List<? extends String> items) throws Exception {
                        for (String item : items) {
                            log.info(">> " + item);
                        }
                    }
                }).build();
    }

    @Bean
    public Job job1() {
        return this.jobBuilderFactory.get("job1").start(step1()).next(step2()).build();
    }

    @Bean
    public Job job2(){
        return jobBuilderFactory.get("job2").start(stepBuilderFactory.get("job2step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute( StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("This job is from ZCS-----");
                return RepeatStatus.FINISHED;
            }
        }).build()).build();
    }

}
