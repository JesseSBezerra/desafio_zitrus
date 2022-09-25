package br.com.jdsb.usuarioapi.resource.impl;

import br.com.jdsb.usuarioapi.domain.dto.UsuarioDTO;
import br.com.jdsb.usuarioapi.resource.UsuarioResource;
import br.com.jdsb.usuarioapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UsuarioResourceImpl implements UsuarioResource {

    private final ModelMapper mapper;
    private final UsuarioService service;

    @Override
    public ResponseEntity<UsuarioDTO> create(UsuarioDTO dto) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<UsuarioDTO> findById(Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id),UsuarioDTO.class));
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x,UsuarioDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<UsuarioDTO> update(Long id, UsuarioDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto),UsuarioDTO.class));
    }

    @Override
    public ResponseEntity<UsuarioDTO> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
