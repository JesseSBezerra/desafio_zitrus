package br.com.jdsb.usuarioapi.service.imp;

import br.com.jdsb.usuarioapi.model.dto.UsuarioDTO;
import br.com.jdsb.usuarioapi.model.entity.Usuario;
import br.com.jdsb.usuarioapi.repository.UsuarioRepository;
import br.com.jdsb.usuarioapi.service.exceptions.UsuarioJaCadastradoException;
import br.com.jdsb.usuarioapi.service.exceptions.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceImplTest {

    private static final Long ID  = 1L;
    private static final Integer INDEX   = 0;
    private static final String LOGIN     = "jesse";
    private static final String SENHA     = "123";
    private static final boolean ADMIN     = true;

    private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String USUARIO_JA_CADASTRADO = "Usuário Já Cadastrado";


    @InjectMocks
    private UsuarioServiceImpl service;
    @Mock
    private UsuarioRepository repository;
    @Mock
    private  ModelMapper mapper;
    @Mock
    private PasswordEncoder encoder;
    private Usuario usuario;

    private Optional<Usuario> optionalUsuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void whenFindByIdThenReturnAnUsarioInstance() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Usuario(ID,LOGIN,SENHA,ADMIN)));

        Usuario response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGIN, response.getLogin());
        assertEquals(SENHA, response.getSenha());
    }

    @Test
    void whenFindByLoginThenReturnAnUsarioInstance() {
        when(repository.findByLogin(anyString())).thenReturn(Optional.of(new Usuario(ID,LOGIN,SENHA,ADMIN)));

        Usuario response = service.findByLogin(LOGIN);

        assertNotNull(response);

        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGIN, response.getLogin());
        assertEquals(SENHA, response.getSenha());
    }

    @Test
    void whenFindByIdThenReturnAnUsuarioNaoEncontradoException() {

        when(repository.findById(anyLong()))
                .thenThrow(new UsuarioNaoEncontradoException(USUARIO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(UsuarioNaoEncontradoException.class, ex.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnUsuarioJaCadastradoException() {
        when(repository.findByLogin(anyString())).thenReturn(optionalUsuario);

        try{
            optionalUsuario.get().setId(2L);
            service.create(usuarioDTO);
        } catch (Exception ex) {
            assertEquals(UsuarioJaCadastradoException.class, ex.getClass());
            assertEquals(USUARIO_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsuarios() {
        when(repository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Usuario.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(LOGIN, response.get(INDEX).getLogin());
        assertEquals(ADMIN, response.get(INDEX).isAdimin());
        assertEquals(SENHA, response.get(INDEX).getSenha());
    }


    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(usuario);

        Usuario response = service.create(usuarioDTO);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGIN, response.getLogin());
        assertEquals(SENHA, response.getSenha());
        assertEquals(ADMIN, response.isAdimin());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(usuario);

        Usuario response = service.update(usuarioDTO);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGIN, response.getLogin());
        assertEquals(SENHA, response.getSenha());
        assertEquals(ADMIN, response.isAdimin());
    }

    @Test
    void whenUpdateThenReturnAnUsuarioJaCadastradoException() {
        when(repository.findByLogin(anyString())).thenReturn(optionalUsuario);

        try{
            optionalUsuario.get().setId(2L);
            service.update(usuarioDTO);
        } catch (Exception ex) {
            assertEquals(UsuarioJaCadastradoException.class, ex.getClass());
            assertEquals(USUARIO_JA_CADASTRADO, ex.getMessage());
        }
    }


    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyLong())).thenReturn(optionalUsuario);
        doNothing().when(repository).deleteById(anyLong());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void whenDeleteThenReturnUsuarioNaoEncontradoException() {
        when(repository.findById(anyLong()))
                .thenThrow(new UsuarioNaoEncontradoException(USUARIO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(UsuarioNaoEncontradoException.class, ex.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startUsuario() {
        usuario = new Usuario(ID,LOGIN,SENHA,ADMIN);
        usuarioDTO = new UsuarioDTO(ID,LOGIN,SENHA,ADMIN);
        optionalUsuario = Optional.of(new Usuario(ID,LOGIN,SENHA,ADMIN));
    }
}