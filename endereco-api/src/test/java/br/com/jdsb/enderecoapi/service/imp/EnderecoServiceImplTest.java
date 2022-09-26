package br.com.jdsb.enderecoapi.service.imp;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import br.com.jdsb.enderecoapi.model.entity.Endereco;
import br.com.jdsb.enderecoapi.repository.EnderecoRepository;
import br.com.jdsb.enderecoapi.service.exceptions.EnderecoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class EnderecoServiceImplTest {

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
    private EnderecoServiceImpl service;
    @Mock
    private EnderecoRepository repository;
    @Mock
    private ModelMapper mapper;

    private Endereco endereco;

    private Optional<Endereco> optionalEndereco;

    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startEndereco();
    }

    @Test
    void whenFindByIdThenReturnAnEnderecoInstance() {
        when(repository.findById(anyLong())).thenReturn(optionalEndereco);

        Endereco response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Endereco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGRADOURO, response.getLogradouro());
        assertEquals(COMPLEMENTO, response.getComplemento());
        assertEquals(BAIRRO, response.getBairro());
        assertEquals(LOCALIDADE, response.getLocalidade());
        assertEquals(CEP, response.getCep());
        assertEquals(UF, response.getUf());
    }

    @Test
    void whenFindByIdThenReturnAnEnderecoNaoEncontradoException() {

        when(repository.findById(anyLong()))
                .thenThrow( new EnderecoNaoEncontradoException(ENDERECO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(EnderecoNaoEncontradoException.class, ex.getClass());
            assertEquals(ENDERECO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(endereco);

        Endereco response = service.create(enderecoDTO);

        assertNotNull(response);
        assertEquals(Endereco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGRADOURO, response.getLogradouro());
        assertEquals(COMPLEMENTO, response.getComplemento());
        assertEquals(BAIRRO, response.getBairro());
        assertEquals(LOCALIDADE, response.getLocalidade());
        assertEquals(CEP, response.getCep());
        assertEquals(UF, response.getUf());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(endereco);

        Endereco response = service.update(enderecoDTO);

        assertNotNull(response);
        assertEquals(Endereco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGRADOURO, response.getLogradouro());
        assertEquals(COMPLEMENTO, response.getComplemento());
        assertEquals(BAIRRO, response.getBairro());
        assertEquals(LOCALIDADE, response.getLocalidade());
        assertEquals(CEP, response.getCep());
        assertEquals(UF, response.getUf());
    }

    @Test
    void whenFindAllThenReturnAnListOfEnderecos() {
        when(repository.findAll()).thenReturn(List.of(endereco));

        List<Endereco> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Endereco.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(LOGRADOURO, response.get(INDEX).getLogradouro());
        assertEquals(COMPLEMENTO, response.get(INDEX).getComplemento());
        assertEquals(BAIRRO, response.get(INDEX).getBairro());
        assertEquals(LOCALIDADE, response.get(INDEX).getLocalidade());
        assertEquals(CEP, response.get(INDEX).getCep());
        assertEquals(UF, response.get(INDEX).getUf());
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyLong())).thenReturn(optionalEndereco);
        doNothing().when(repository).delete(any());
        service.delete(ID);
        verify(repository, times(1)).delete(any());
    }

    private void startEndereco() {
        endereco = new Endereco(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF);
        enderecoDTO = new EnderecoDTO(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF);
        optionalEndereco = Optional.of(new Endereco(ID,LOGRADOURO,COMPLEMENTO,CEP,BAIRRO,LOCALIDADE,UF));
    }
}