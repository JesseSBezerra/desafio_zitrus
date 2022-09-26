package br.com.jdsb.clienteapi.resource.impl;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import br.com.jdsb.clienteapi.resource.ClienteResource;
import br.com.jdsb.clienteapi.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClienteResourceImpl implements ClienteResource {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ClienteService service;

    @Override
    public ResponseEntity<ClienteDTO> create(ClienteDTO dto) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ClienteDTO> findById(Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id),ClienteDTO.class));
    }

    @Override
    public ResponseEntity<ClienteDTO> findByCpf(String cpf) {
        return ResponseEntity.ok().body(mapper.map(service.findByCpf(cpf),ClienteDTO.class));
    }

    @Override
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x,ClienteDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ClienteDTO> update(Long id, ClienteDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto),ClienteDTO.class));
    }

    @Override
    public ResponseEntity<ClienteDTO> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
