package com.example.salesapi.services;

import com.example.salesapi.domain.dto.ClienteDTO;
import com.example.salesapi.domain.entity.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente findById(Long id);
    ClienteDTO create(ClienteDTO dto);
    ClienteDTO findByCpf(String cpf);
    List<ClienteDTO> findAll();
    ClienteDTO update(Long id,ClienteDTO clienteDTO);

    ClienteDTO detele(Long id);


}
