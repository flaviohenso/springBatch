package com.udemy.primeiroprojetospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.primeiroprojetospringbatch.listener.JobLoggerListener;
import com.udemy.primeiroprojetospringbatch.validator.ParameterValidator;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

	private JobBuilderFactory jobBuilderFactory; //para criar job com spring batch
	
	private ParameterValidator parameterValidator; //classe para fazer validação dos parametros de entrada
	
	private JobLoggerListener jobLoggerListener; //classe de Listener
	
	@Autowired
	public BatchConfig(JobBuilderFactory jobBuilderFactory, ParameterValidator parameterValidator,JobLoggerListener jobLoggerListener) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.parameterValidator = parameterValidator;
		this.jobLoggerListener = jobLoggerListener;
	}

	@Bean
	public Job imprimeOlaJob(Step imprimeOlaStep) {
		return jobBuilderFactory
				.get("imprimeOlaJob")
				.start(imprimeOlaStep)
				.validator(parameterValidator)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job imprimeParOuImparJob(Step imprimeParouImparStep) {
		return jobBuilderFactory
				.get("imprimeParOuImparJob")
				.incrementer(new RunIdIncrementer())
				.listener(JobListenerFactoryBean.getListener(jobLoggerListener))
				.start(imprimeParouImparStep)
				.build();
	}
	
	
}
