package com.udemy.primeiroprojetospringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.udemy.primeiroprojetospringbatch.listener.StepLoggerListener;

@Configuration
public class ImprimeOlaStepConfig {

	
	private JobRepository jobRepository;
	private StepLoggerListener stepLoggerListener;
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	public ImprimeOlaStepConfig(JobRepository jobRepository, StepLoggerListener stepLoggerListener, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.stepLoggerListener = stepLoggerListener;
		this.transactionManager = transactionManager;
	}



	@Bean
	public Step imprimeOlaStep(Tasklet imprimeOlaTaskletBean) {
		return new StepBuilder("imprimeOlaStep", jobRepository)
				.listener(stepLoggerListener)
				.tasklet(imprimeOlaTaskletBean, transactionManager)
				.build();
	}

}

//@Bean
//@StepScope
//public Tasklet imprimeOlaTasklet(@Value("#{jobParameters['nome']}")String nome) {
//	return new Tasklet() {
//		
//		@Override
//		public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//			System.out.println(String.format("Olá %s",nome));						
//			return RepeatStatus.FINISHED;
//		}
//	};
//}
