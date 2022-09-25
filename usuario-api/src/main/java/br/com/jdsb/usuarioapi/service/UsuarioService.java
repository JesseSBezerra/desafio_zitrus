package br.com.jdsb.usuarioapi.service;

import br.com.jdsb.usuarioapi.model.dto.UsuarioDTO;
import br.com.jdsb.usuarioapi.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String USUARIO_JA_CADASTRADO = "Usuário Já Cadastrado";

    public Usuario findByLogin(String login);
    public Usuario findById(Long id);
    public Usuario create(UsuarioDTO usuario);
    public Usuario update(UsuarioDTO usuario);
    public List<Usuario> findAll();
    public void delete(Long id);
}
