package br.com.jdsb.enderecoapi.resource.impl;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import br.com.jdsb.enderecoapi.resource.EnderecoResource;
import br.com.jdsb.enderecoapi.service.EnderecoService;
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
public class EnderecoResourceImpl implements EnderecoResource {

    private final ModelMapper mapper;
    private final EnderecoService service;

    @Override
    public ResponseEntity<EnderecoDTO> create(EnderecoDTO dto) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<EnderecoDTO> findById(Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id),EnderecoDTO.class));
    }

    @Override
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x,EnderecoDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<EnderecoDTO> update(Long id, EnderecoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto),EnderecoDTO.class));
    }

    @Override
    public ResponseEntity<EnderecoDTO> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
