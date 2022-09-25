package br.com.jdsb.clienteapi.resource;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cliente")
public interface ClienteResource {

    @PostMapping
    ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<ClienteDTO> findById(@PathVariable Long id);

    @GetMapping("/cpf/{cpf}")
    ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf);

    @GetMapping
    ResponseEntity<List<ClienteDTO>> findAll();

    @PutMapping(value = "/{id}")
    ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id);
}
