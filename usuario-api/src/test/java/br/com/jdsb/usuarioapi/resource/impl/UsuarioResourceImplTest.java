package br.com.jdsb.usuarioapi.resource.impl;

import br.com.jdsb.usuarioapi.model.dto.UsuarioDTO;
import br.com.jdsb.usuarioapi.model.entity.Usuario;
import br.com.jdsb.usuarioapi.service.imp.UsuarioServiceImpl;
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
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioResourceImplTest {

    private static final Long ID  = 1L;
    private static final Integer INDEX   = 0;
    private static final String LOGIN     = "jesse";
    private static final String SENHA     = "123";
    private static final boolean ADMIN     = true;

    private Usuario usuario = new Usuario();
    private UsuarioDTO usuarioDTO = new UsuarioDTO();

    @InjectMocks
    private UsuarioResourceImpl resource;

    @Mock
    private UsuarioServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(usuario);

        ResponseEntity<UsuarioDTO> response = resource.create(usuarioDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }


    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(usuario);
        when(mapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(LOGIN, response.getBody().getLogin());
        assertEquals(SENHA, response.getBody().getSenha());
        assertEquals(ADMIN, response.getBody().isAdimin());
    }


    @Test
    void whenFindAllThenReturnAListOfUsuarioDTO() {
        when(service.findAll()).thenReturn(List.of(usuario));
        when(mapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<List<UsuarioDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UsuarioDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(LOGIN, response.getBody().get(INDEX).getLogin());
        assertEquals(SENHA, response.getBody().get(INDEX).getSenha());
        assertEquals(ADMIN, response.getBody().get(INDEX).isAdimin());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(usuarioDTO)).thenReturn(usuario);
        when(mapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = resource.update(ID, usuarioDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(LOGIN, response.getBody().getLogin());
        assertEquals(SENHA, response.getBody().getSenha());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<UsuarioDTO> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyLong());
    }

    private void startUsuario() {
        usuario = new Usuario(ID,LOGIN,SENHA,ADMIN);
        usuarioDTO = new UsuarioDTO(ID,LOGIN,SENHA,ADMIN);
    }
}