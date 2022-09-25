package br.com.jdsb.usuarioapi.service.imp;

import br.com.jdsb.usuarioapi.domain.dto.UsuarioDTO;
import br.com.jdsb.usuarioapi.domain.entity.Usuario;
import br.com.jdsb.usuarioapi.repository.UsuarioRepository;
import br.com.jdsb.usuarioapi.service.UsuarioService;
import br.com.jdsb.usuarioapi.service.exceptions.UsuarioJaCadastradoException;
import br.com.jdsb.usuarioapi.service.exceptions.UsuarioNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    private final ModelMapper mapper;

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(()-> new UsuarioNaoEncontradoException(USUARIO_NAO_ENCONTRADO));
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException(USUARIO_NAO_ENCONTRADO));
    }

    @Override
    public Usuario create(UsuarioDTO usuario) {
        if(repository.findByLogin(usuario.getLogin()).isPresent())
            throw new UsuarioJaCadastradoException(USUARIO_JA_CADASTRADO);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario user = mapper.map(usuario,Usuario.class);
        return repository.save(user);
    }

    @Override
    public Usuario update(UsuarioDTO usuario) {
        Optional<Usuario> usuarioBusca = repository.findByLogin(usuario.getLogin());
        if(usuarioBusca.isPresent()){
            if(!usuarioBusca.get().getLogin().equals(usuario.getLogin())){
                throw new UsuarioJaCadastradoException(USUARIO_JA_CADASTRADO);
            }
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario user = mapper.map(usuario,Usuario.class);
        return repository.save(user);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
      repository.deleteById(id);
    }
}
