package br.com.jdsb.cadastrodeclientes.validators;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


public class ClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("supports: " + ClienteDTO.class.equals(clazz));
		return ClienteDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		System.out.println("validate: true");

		ClienteDTO f = (ClienteDTO) object;

		if(f.getEmail().isBlank()){
			errors.rejectValue("email", "cliente.campoRequerido");
		}


		if(f.getNome().isBlank()){
			errors.rejectValue("nome", "cliente.campoRequerido");
		}

		if(f.getCpf().isBlank()){
			errors.rejectValue("cpf", "cliente.campoRequerido");
		}

		if(!f.getCpf().isBlank()) {
			CPFValidator cpfValidator = new CPFValidator();
			List<ValidationMessage> validacoes = cpfValidator.invalidMessagesFor(f.getCpf().replace(".", "").replace("-", ""));
	        if(validacoes.size()>0){
				errors.rejectValue("cpf", "cliente.cpf.invalido");
			}
		}
		if(f.getEndereco().getCep().isBlank()){
			errors.rejectValue("endereco.cep", "cliente.campoRequerido");
		}
	}

}
