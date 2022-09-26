package br.com.jdsb.enderecoapi.resource.impl;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import br.com.jdsb.enderecoapi.model.entity.Endereco;
import br.com.jdsb.enderecoapi.service.imp.EnderecoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class EnderecoResourceImplTest {

    private static final Integer INDEX   = 0;
    private final Long ID = 1L;
    private final String LOGRADOURO = "Rua Mariano Manoel da Silva";
    private final String COMPLEMENTO = "Numero: 433";
    private final String CEP = "55125000";
    private final String BAIRRO = "Centro";
    private final String LOCALIDADE = "Toritama";
    private final String UF = "PE";

    private static final String ENDERECO_NAO_ENCONTRADO = "Endereco não encontrado!";

    @InjectMocks
    private EnderecoResourceImpl resource;
    @Mock
    private EnderecoServiceImpl service;
    @Mock
    private ModelMapper mapper;

    private Endereco endereco;
    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startEndereco();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(endereco);
        when(mapper.map(any(), any())).thenReturn(enderecoDTO);

        ResponseEntity<EnderecoDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(EnderecoDTO.class, response.getBody().getClass());


        assertEquals(ID, response.getBody().getId());
        assertEquals(LOGRADOURO, response.getBody().getLogradouro());
        assertEquals(COMPLEMENTO, response.getBody().getComplemento());
        assertEquals(BAIRRO, response.getBody().getBairro());
        assertEquals(LOCALIDADE, response.getBody().getLocalidade());
        assertEquals(CEP, response.getBody().getCep());
        assertEquals(UF, response.getBody().getUf());
    }


    @Test
    void whenFindAllThenReturnAListOfUsuarioDTO() {
        when(service.findAll()).thenReturn(List.of(endereco));
        when(mapper.map(any(), any())).thenReturn(enderecoDTO);

        ResponseEntity<List<EnderecoDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(EnderecoDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(LOGRADOURO, response.getBody().get(INDEX).getLogradouro());
        assertEquals(COMPLEMENTO, response.getBody().get(INDEX).getComplemento());
        assertEquals(BAIRRO, response.getBody().get(INDEX).getBairro());
        assertEquals(LOCALIDADE, response.getBody().get(INDEX).getLocalidade());
        assertEquals(CEP, response.getBody().get(INDEX).getCep());
        assertEquals(UF, response.getBody().get(INDEX).getUf());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(enderecoDTO)).thenReturn(endereco);
        when(mapper.map(any(), any())).thenReturn(enderecoDTO);

        ResponseEntity<EnderecoDTO> response = resource.update(ID, enderecoDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(EnderecoDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(LOGRADOURO, response.getBody().getLogradouro());
        assertEquals(COMPLEMENTO, response.getBody().getComplemento());
        assertEquals(BAIRRO, response.getBody().getBairro());
        assertEquals(LOCALIDADE, response.getBody().getLocalidade());
        assertEquals(CEP, response.getBody().getCep());
        assertEquals(UF, response.getBody().getUf());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<EnderecoDTO> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyLong());
    }

    private void startEndereco() {
        endereco = new Endereco(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF);
        enderecoDTO = new EnderecoDTO(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF);
    }
}