package com.udemy.primeiroprojetospringbatch.listener;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Component
public class StepLoggerListener {
	
	private static String START_MESSAGE = "Step iniciado";
	private static String END_MESSAGE = "Step finalizado";

	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		System.out.println(START_MESSAGE + " " + stepExecution.getSummary());
	}
	
	@AfterStep
	public void afterStep(StepExecution stepExecution) {
		System.out.println(END_MESSAGE + " " + stepExecution.getJobExecution().getJobInstance().getJobName());
	}
}
