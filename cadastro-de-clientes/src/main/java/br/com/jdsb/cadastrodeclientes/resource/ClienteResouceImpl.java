package br.com.jdsb.cadastrodeclientes.resource;

import br.com.jdsb.cadastrodeclientes.feign.ClienteFeign;
import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResouceImpl {

    @Autowired
    private ClienteFeign client;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return client.getClientes();
    }

}
