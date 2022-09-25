package br.com.jdsb.usuarioapi.repository;

import br.com.jdsb.usuarioapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByLogin(String Login);

}
