package br.com.jdsb.clienteapi.service.impl;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import br.com.jdsb.clienteapi.model.dto.EnderecoDTO;
import br.com.jdsb.clienteapi.model.entity.Cliente;
import br.com.jdsb.clienteapi.model.entity.Endereco;
import br.com.jdsb.clienteapi.repository.ClienteRepository;
import br.com.jdsb.clienteapi.service.exceptions.ClienteJaCadastradoException;
import br.com.jdsb.clienteapi.service.exceptions.ClienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

    private final Endereco endereco = new Endereco(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF);

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
    public static final String CLIENTE_JA_CADASTRADO = "Cliente Ja Cadastrado";

    @InjectMocks
    private ClienteServiceImpl service;
    @Mock
    private ClienteRepository repository;
    @Mock
    private ModelMapper mapper;


    private Cliente cliente;
    private Optional<Cliente> optionalCliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void whenFindByIdThenReturnAClienteInstance() {
        when(repository.findById(anyLong())).thenReturn(optionalCliente);

        Cliente response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Cliente.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());
    }

    @Test
    void whenFindByLoginThenReturnAnClienteInstance() {
        when(repository.findByCpf(anyString())).thenReturn(optionalCliente);

        Cliente response = service.findByCpf(CPF);

        assertNotNull(response);

        assertEquals(Cliente.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());
    }

    @Test
    void whenFindByIdThenReturnAnUsuarioNaoEncontradoException() {

        when(repository.findById(anyLong()))
                .thenThrow(new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ClienteNaoEncontradoException.class, ex.getClass());
            assertEquals(CLIENTE_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnUsuarioJaCadastradoException() {
        when(repository.findByCpf(anyString())).thenReturn(optionalCliente);

        try{
            optionalCliente.get().setId(2L);
            service.create(clienteDTO);
        } catch (Exception ex) {
            assertEquals(ClienteJaCadastradoException.class, ex.getClass());
            assertEquals(CLIENTE_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsuarios() {
        when(repository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Cliente.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(CPF, response.get(INDEX).getCpf());
        assertEquals(DATA_NASCIMENTO, response.get(INDEX).getDataNascimento());
        assertEquals(endereco, response.get(INDEX).getEndereco());
    }


    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(cliente);

        Cliente response = service.create(clienteDTO);

        assertNotNull(response);
        assertEquals(Cliente.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(cliente);

        Cliente response = service.update(clienteDTO);

        assertNotNull(response);
        assertEquals(Cliente.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByCpf(anyString())).thenReturn(optionalCliente);

        try{
            optionalCliente.get().setId(2L);
            service.update(clienteDTO);
        } catch (Exception ex) {
            assertEquals(ClienteJaCadastradoException.class, ex.getClass());
            assertEquals(CLIENTE_JA_CADASTRADO, ex.getMessage());
        }
    }


    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyLong())).thenReturn(optionalCliente);
        doNothing().when(repository).delete(any());
        service.delete(ID);
        verify(repository, times(1)).delete(any());
    }

    @Test
    void whenDeleteThenReturnUsuarioNaoEncontradoException() {
        when(repository.findById(anyLong()))
                .thenThrow(new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ClienteNaoEncontradoException.class, ex.getClass());
            assertEquals(CLIENTE_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startCliente() {
        cliente = new Cliente(ID,NOME,EMAIL,CPF,DATA_NASCIMENTO,TELEFONE,endereco);
        clienteDTO = new ClienteDTO(ID,NOME,EMAIL,DATA_NASCIMENTO,CPF,TELEFONE,new EnderecoDTO(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF));
        optionalCliente = Optional.of(cliente);
    }
}