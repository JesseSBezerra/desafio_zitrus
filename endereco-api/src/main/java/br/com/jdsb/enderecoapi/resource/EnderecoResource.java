package br.com.jdsb.enderecoapi.resource;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/endereco")
public interface EnderecoResource {

    @PostMapping
    ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<EnderecoDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<EnderecoDTO>> findAll();

    @PutMapping(value = "/{id}")
    ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO dto);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> delete(@PathVariable Long id);

}
