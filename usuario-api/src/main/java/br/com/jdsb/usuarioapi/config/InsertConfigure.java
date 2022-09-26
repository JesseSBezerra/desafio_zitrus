package br.com.jdsb.usuarioapi.config;

import br.com.jdsb.usuarioapi.model.entity.Usuario;
import br.com.jdsb.usuarioapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InsertConfigure {

    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;

    @Bean
    public void insereUsuarioPadrao(){
        Usuario usuario = new Usuario();
        usuario.setLogin("zitrino");
        usuario.setSenha(encoder.encode("venhaserfeliz"));
        usuario.setAdimin(true);

        if(repository.findByLogin(usuario.getLogin()).isEmpty()){
           repository.save(usuario);
        }

    }
}
