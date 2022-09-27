package br.com.jdsb.cadastrodeclientes.feign;

import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cliente-api")
public interface ClienteFeign {

    @PostMapping(value = "/api/cliente/")
    ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto);

    @GetMapping(value = "/api/cliente/")
    ResponseEntity<List<ClienteDTO>> getClientes();

    @GetMapping(value = "/api/cliente/{id}")
    ResponseEntity<ClienteDTO> findById(@PathVariable Long id);

    @GetMapping(value = "/api/cliente/cpf/{cpf}")
    ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf);

    @DeleteMapping(value = "/api/cliente/{id}")
    ResponseEntity<ClienteDTO> deleteById(@PathVariable Long id);

    @PutMapping(value = "/api/cliente/{id}")
    ResponseEntity<ClienteDTO> update(@PathVariable Long id,@RequestBody ClienteDTO dto);
}
