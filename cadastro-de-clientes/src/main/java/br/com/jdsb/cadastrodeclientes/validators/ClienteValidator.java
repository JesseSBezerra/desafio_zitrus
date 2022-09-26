package br.com.jdsb.cadastrodeclientes.validators;

import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("supports: " + ClienteDTO.class.equals(clazz));
		return ClienteDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		System.out.println("validate: true");

		ClienteDTO f = (ClienteDTO) object;
		
		LocalDate entrada = f.getDataNascimento();
		
		if (f.getDataNascimento() != null && f.getDataNascimento() != null) {
			if (f.getDataNascimento().isBefore(entrada.plusDays(-3))) {
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
	}

}
