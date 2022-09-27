package br.com.jdsb.cadastrodeclientes.feign;

import br.com.jdsb.cadastrodeclientes.model.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "endereco-api",url = "http://localhost:8765/endereco-api/")
public interface EnderecoFeign {

    @PostMapping(value = "/api/endereco/")
    ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoDTO dto);

    @PutMapping(value = "/api/endereco/{id}")
    ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO dto);
}
