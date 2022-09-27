package br.com.jdsb.cadastrodeclientes.feign;

import br.com.jdsb.cadastrodeclientes.model.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-api",url = "localhost:8765/usuario-api/")
public interface UsuarioFeign {

    @GetMapping("/api/usuario/login/{login}")
    ResponseEntity<UsuarioDTO> findByLogin(@PathVariable String login);
}
