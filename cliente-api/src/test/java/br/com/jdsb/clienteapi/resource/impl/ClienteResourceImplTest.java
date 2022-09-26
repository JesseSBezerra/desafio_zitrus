package br.com.jdsb.clienteapi.resource.impl;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import br.com.jdsb.clienteapi.model.dto.EnderecoDTO;
import br.com.jdsb.clienteapi.model.entity.Cliente;
import br.com.jdsb.clienteapi.model.entity.Endereco;
import br.com.jdsb.clienteapi.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class ClienteResourceImplTest {

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

    private final Endereco endereco = new Endereco(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF);

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
    public static final String CLIENTE_JA_CADASTRADO = "Cliente Ja Cadastrado";

    @InjectMocks
    private  ClienteResourceImpl resource;

    @Mock
    private ClienteServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(cliente);

        ResponseEntity<ClienteDTO> response = resource.create(clienteDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenFindByCpfThenReturnSuccess() {
        when(service.findByCpf(anyString())).thenReturn(cliente);
        when(mapper.map(any(), any())).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = resource.findByCpf(CPF);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ClienteDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(CPF, response.getBody().getCpf());
        assertEquals(DATA_NASCIMENTO, response.getBody().getDataNascimento());
        assertEquals(clienteDTO.getEndereco(), response.getBody().getEndereco());
    }


    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(cliente);
        when(mapper.map(any(), any())).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ClienteDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(CPF, response.getBody().getCpf());
        assertEquals(DATA_NASCIMENTO, response.getBody().getDataNascimento());
        assertEquals(clienteDTO.getEndereco(), response.getBody().getEndereco());
    }


    @Test
    void whenFindAllThenReturnAListOfClienteDTO() {
        when(service.findAll()).thenReturn(List.of(cliente));
        when(mapper.map(any(), any())).thenReturn(clienteDTO);

        ResponseEntity<List<ClienteDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(ClienteDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(CPF, response.getBody().get(INDEX).getCpf());
        assertEquals(DATA_NASCIMENTO, response.getBody().get(INDEX).getDataNascimento());
        assertEquals(clienteDTO.getEndereco(), response.getBody().get(INDEX).getEndereco());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(clienteDTO)).thenReturn(cliente);
        when(mapper.map(any(), any())).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = resource.update(ID, clienteDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ClienteDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(CPF, response.getBody().getCpf());
        assertEquals(DATA_NASCIMENTO, response.getBody().getDataNascimento());
        assertEquals(clienteDTO.getEndereco(), response.getBody().getEndereco());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<ClienteDTO> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyLong());
    }

    private void startCliente() {
        cliente = new Cliente(ID,NOME,EMAIL,CPF,DATA_NASCIMENTO,endereco);
        clienteDTO = new ClienteDTO(ID,NOME,EMAIL,DATA_NASCIMENTO,CPF,new EnderecoDTO(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF));
    }
}