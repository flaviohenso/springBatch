package com.udemy.primeiroprojetospringbatch.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ParameterValidator implements JobParametersValidator {

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		String nome = parameters.getString("nome");
		if (!StringUtils.hasText(nome)) {
			throw new JobParametersInvalidException("O parametro" + " nome é obrigatorio!");
		} else if (!StringUtils.endsWithIgnoreCase(nome, "CSV")) {
			throw new JobParametersInvalidException("O arquivo não " + "esta no formato correto");
		}
	}

}
