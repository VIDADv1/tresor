package com.example.tresor.job;

import com.example.tresor.listener.JobCompletionListener;
import com.example.tresor.processor.MapItemProcessor;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class Job {

    @Bean
    public org.springframework.batch.core.Job tresorJob(JobRepository jobRepository,
                                                        JobExecutionListener listener, Step step1) {
        return new JobBuilder("tresorJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step tresorStep(JobRepository jobRepository, ItemReader<String> reader,
                           ItemProcessor<String, Object> processor,
                           ItemWriter<Object> writer) {
        return new StepBuilder("tresorStep", jobRepository)
                .<String, Object> chunk(1, new ResourcelessTransactionManager())
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<String> reader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("mapItemReader")
                .resource(new ClassPathResource("input.txt"))
                .lineMapper(new PassThroughLineMapper())
                .build();
    }

    @Bean
    @StepScope
    public MapItemProcessor processor() {
        return new MapItemProcessor();
    }

    @Bean
    public ItemWriter<Object> writer() {
        return chunk -> {
            // no-op
        };
    }

    @Bean
    public JobExecutionListener jobListener() {
        return new JobCompletionListener();
    }
}
