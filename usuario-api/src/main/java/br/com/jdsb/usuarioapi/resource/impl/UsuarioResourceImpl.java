package br.com.jdsb.usuarioapi.resource.impl;

import br.com.jdsb.usuarioapi.model.dto.UsuarioDTO;
import br.com.jdsb.usuarioapi.resource.UsuarioResource;
import br.com.jdsb.usuarioapi.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioResourceImpl implements UsuarioResource {

    @Autowired
    private  ModelMapper mapper;
    @Autowired
    private  UsuarioService service;

    @Override
    public ResponseEntity<UsuarioDTO> create(UsuarioDTO dto) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<UsuarioDTO> findById(Long id) {
        UsuarioDTO obj = mapper.map(service.findById(id),UsuarioDTO.class);
        return ResponseEntity.ok().body(obj);
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
