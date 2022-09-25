package br.com.jdsb.clienteapi.service;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import br.com.jdsb.clienteapi.model.entity.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente findById(Long id);
    public Cliente create(ClienteDTO dto);
    public Cliente findByCpf(String cpf);
    public List<Cliente> findAll();
    public Cliente update(ClienteDTO clienteDTO);
    public void detele(Long id);


}
