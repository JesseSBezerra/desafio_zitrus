package br.com.jdsb.clienteapi.resource.impl;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import br.com.jdsb.clienteapi.resource.ClienteResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteResourceImpl implements ClienteResource {

    @Override
    public ResponseEntity<ClienteDTO> create(ClienteDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClienteDTO> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ClienteDTO> findById(String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<ClienteDTO> update(Long id, ClienteDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClienteDTO> delete(Long id) {
        return null;
    }
}
