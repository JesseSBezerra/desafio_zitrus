package br.com.jdsb.cadastrodeclientes.feign;

import br.com.jdsb.cadastrodeclientes.model.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cep-api",
        url = "https://opencep.com"
)
public interface CepFeign {
    @GetMapping({"/v1/{cep}"})
    ResponseEntity<EnderecoDTO> findByCep(@PathVariable String cep);
}
