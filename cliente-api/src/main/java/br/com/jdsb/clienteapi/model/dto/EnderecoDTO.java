package com.example.salesapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private String complemento;
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;
}
