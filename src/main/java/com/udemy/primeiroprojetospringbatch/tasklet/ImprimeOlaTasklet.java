package com.udemy.primeiroprojetospringbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeOlaTasklet {
	
	@Bean
	public Tasklet imprimeOlaTaskletBean() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				String nome = chunkContext.getStepContext().getJobParameters().get("nome").toString();
				System.out.println(String.format("Olá Mundo %s", nome));
				return RepeatStatus.FINISHED;
			}
		};
	}
}
