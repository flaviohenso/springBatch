package com.udemy.primeiroprojetospringbatch.step;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.primeiroprojetospringbatch.listener.StepLoggerListener;

@Configuration
public class ImprimeParouImparStepConfig {

	private StepBuilderFactory stepBuilderFactory;
	private StepLoggerListener stepLoggerListener;

	@Autowired
	public ImprimeParouImparStepConfig(StepBuilderFactory stepBuilderFactory, StepLoggerListener stepLoggerListener) {
		this.stepBuilderFactory = stepBuilderFactory;
		this.stepLoggerListener = stepLoggerListener;
	}

	@Bean
	public Step imprimeParouImparStep() {
		return stepBuilderFactory.get("imprimiParouImpar").<Integer, String>chunk(5).reader(this.contaAteDez())
				.processor(this.parOuImparProcessor()).writer(imprimirWriter()).listener(stepLoggerListener).build();
	}

	private ItemWriter<? super String> imprimirWriter() {
		return item -> item.forEach(System.out::println);
	}

	private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
		return new FunctionItemProcessor<Integer, String>(
				item -> item % 2 == 0 ? String.format("O numero é %s é par", item)
						: String.format("O numero é %s é impar", item));
	}

	private IteratorItemReader<Integer> contaAteDez() {
		List<Integer> ateDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<Integer>(ateDez.iterator());
	}
}
