package com.udemy.primeiroprojetospringbatch.step;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeOlaStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step imprimeOlaStep(Tasklet imprimeOlaTasklet) {
		return stepBuilderFactory
				.get("imprimeOlaStep")
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
