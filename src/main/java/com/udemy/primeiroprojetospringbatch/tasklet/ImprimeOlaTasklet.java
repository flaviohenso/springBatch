package com.udemy.primeiroprojetospringbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@StepScope //necessario para que consiga acessar o Jobparameters
public class ImprimeOlaTasklet implements Tasklet {
	
	@Value("#{jobParameters['nome']}")String nome;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println(String.format("Olá Mundo %s", nome));
		return RepeatStatus.FINISHED;
	}
	
//	@Bean
//	@StepScope
//	public Tasklet imprimeOlaTasklet(@Value("#{jobParameters['nome']}")String nome) {
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				System.out.println(String.format("Olá %s",nome));						
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}

}
