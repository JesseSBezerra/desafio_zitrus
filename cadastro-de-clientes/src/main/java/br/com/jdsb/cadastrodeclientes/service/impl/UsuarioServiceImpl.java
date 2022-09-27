package br.com.jdsb.cadastrodeclientes.service.impl;

import br.com.jdsb.cadastrodeclientes.feign.UsuarioFeign;
import br.com.jdsb.cadastrodeclientes.model.dto.UsuarioDTO;
import br.com.jdsb.cadastrodeclientes.service.exception.SenhaInvalidaException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioFeign feign;

    @Autowired
    private PasswordEncoder encoder;

    public UserDetails autenticar(UsuarioDTO usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhaOK = encoder.matches(usuario.getSenha(),user.getPassword());
        if(senhaOK){
            return user;
        }
        throw new SenhaInvalidaException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO usuario;
        try {
            usuario = feign.findByLogin(username).getBody();
            String[] roles = usuario.isAdimin() ? new String[]{"ADMIN","USER"} : new String[]{"USER"};
            return User
                    .builder()
                    .username(usuario.getLogin())
                    .password(usuario.getSenha())
                    .roles(roles)
                    .build();

        }catch (FeignException.NotFound ex){
            throw  new UsernameNotFoundException("Usuário não encontrado na base de dados");
        }

    }
}
