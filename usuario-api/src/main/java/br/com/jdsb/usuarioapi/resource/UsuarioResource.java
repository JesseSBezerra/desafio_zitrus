package br.com.jdsb.usuarioapi.resource;

import br.com.jdsb.usuarioapi.domain.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/usuario")
public interface UsuarioResource {

    @PostMapping
    ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<UsuarioDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<UsuarioDTO>> findAll();

    @PutMapping(value = "/{id}")
    ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id);
}
