package br.com.jdsb.enderecoapi.resource.impl;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import br.com.jdsb.enderecoapi.resource.EnderecoResource;
import br.com.jdsb.enderecoapi.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnderecoResourceImpl implements EnderecoResource {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EnderecoService service;

    @Override
    public ResponseEntity<EnderecoDTO> create(EnderecoDTO dto) {
        return ResponseEntity.ok().body(mapper.map(service.create(dto),EnderecoDTO.class));
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
