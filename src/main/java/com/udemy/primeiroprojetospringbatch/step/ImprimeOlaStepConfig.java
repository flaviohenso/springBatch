package com.udemy.primeiroprojetospringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.primeiroprojetospringbatch.listener.StepLoggerListener;

@Configuration
public class ImprimeOlaStepConfig {

	
	private StepBuilderFactory stepBuilderFactory;
	private StepLoggerListener stepLoggerListener;
	
	@Autowired
	public ImprimeOlaStepConfig(StepBuilderFactory stepBuilderFactory,StepLoggerListener stepLoggerListener) {
		this.stepBuilderFactory = stepBuilderFactory;
		this.stepLoggerListener = stepLoggerListener;
	}



	@Bean
	public Step imprimeOlaStep(Tasklet imprimeOlaTasklet) {
		return stepBuilderFactory
				.get("imprimeOlaStep")
				.listener(stepLoggerListener)
				.tasklet(imprimeOlaTasklet)// para etapas simples que nao requer muito processamento de dados
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
