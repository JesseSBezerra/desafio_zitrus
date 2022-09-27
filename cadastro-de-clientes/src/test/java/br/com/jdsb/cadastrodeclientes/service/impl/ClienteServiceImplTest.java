package br.com.jdsb.cadastrodeclientes.service.impl;

import br.com.jdsb.cadastrodeclientes.feign.ClienteFeign;
import br.com.jdsb.cadastrodeclientes.feign.EnderecoFeign;
import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import br.com.jdsb.cadastrodeclientes.model.dto.EnderecoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceImplTest {

    private static final Integer INDEX   = 0;
    private final Long ID = 1L;
    private final String NOME = "Jessé";
    private final String EMAIL = "jessebezerra@hotmail.com.br";
    private final String CPF = "36558737000";
    private final LocalDate DATA_NASCIMENTO = LocalDate.of(2022,4,9);

    private final String LOGRADOURO = "Rua Mariano Manoel da Silva";
    private final String COMPLEMENTO = "Numero: 433";
    private final String CEP = "55125000";
    private final String BAIRRO = "Centro";
    private final String LOCALIDADE = "Toritama";
    private final String UF = "PE";
    private final String TELEFONE = "81997088404";

    @InjectMocks
    private ClienteServiceImpl service;
    @Mock
    private ClienteFeign feign;

    @Mock
    private EnderecoFeign enderecoFeign;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void create() {
        when(enderecoFeign.create(any())).thenReturn(ResponseEntity.of(Optional.of(clienteDTO.getEndereco())));
        ResponseEntity<EnderecoDTO> response = enderecoFeign.create(any());
        assertNotNull(response);

        EnderecoDTO endereco = response.getBody();

        assertNotNull(endereco);
        assertEquals(EnderecoDTO.class,endereco.getClass());
        assertEquals(ID, endereco.getId());
        assertEquals(LOGRADOURO, endereco.getLogradouro());
        assertEquals(COMPLEMENTO, endereco.getComplemento());
        assertEquals(BAIRRO, endereco.getBairro());
        assertEquals(LOCALIDADE, endereco.getLocalidade());
        assertEquals(CEP, endereco.getCep());
        assertEquals(UF, endereco.getUf());
    }

    private void startCliente() {
        clienteDTO = new ClienteDTO(ID,NOME,EMAIL,DATA_NASCIMENTO,CPF,TELEFONE,new EnderecoDTO(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF));
    }
}