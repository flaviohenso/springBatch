package com.udemy.primeiroprojetospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.primeiroprojetospringbatch.listener.JobLoggerListener;
import com.udemy.primeiroprojetospringbatch.validator.ParameterValidator;

@Configuration
public class BatchConfig {

	private JobRepository jobRepository;
	
	private ParameterValidator parameterValidator;
	
	private JobLoggerListener jobLoggerListener;
	
	@Autowired
	public BatchConfig(JobRepository jobRepository, ParameterValidator parameterValidator, JobLoggerListener jobLoggerListener) {
		this.jobRepository = jobRepository;
		this.parameterValidator = parameterValidator;
		this.jobLoggerListener = jobLoggerListener;
	}

	@Bean
	public Job imprimeOlaJob(Step imprimeOlaStep) {
		return new JobBuilder("imprimeOlaJob", jobRepository)
				.start(imprimeOlaStep)
				.validator(parameterValidator)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job imprimeParOuImparJob(Step imprimeParouImparStep) {
		return new JobBuilder("imprimeParOuImparJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(JobListenerFactoryBean.getListener(jobLoggerListener))
				.start(imprimeParouImparStep)
				.build();
	}
	
	
}
