package br.com.jdsb.clienteapi.resource.exceptions;

import br.com.jdsb.clienteapi.service.exceptions.ClienteJaCadastradoException;
import br.com.jdsb.clienteapi.service.exceptions.ClienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
    public static final String CLIENTE_JA_CADASTRADO = "Cliente Ja Cadastrado";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clienteNaoEncontrado() {
        ResponseEntity<StandardError> response = exceptionHandler
                .clienteNaoEncontrado(
                        new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(CLIENTE_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/usuario/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void clienteJaCadastrado() {
        ResponseEntity<StandardError> response = exceptionHandler
                .clienteJaCadastrado(
                        new ClienteJaCadastradoException(CLIENTE_JA_CADASTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(CLIENTE_JA_CADASTRADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}