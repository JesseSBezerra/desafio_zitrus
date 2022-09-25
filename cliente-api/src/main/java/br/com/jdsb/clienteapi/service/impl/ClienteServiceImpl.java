package br.com.jdsb.clienteapi.service.impl;

import br.com.jdsb.clienteapi.model.dto.ClienteDTO;
import br.com.jdsb.clienteapi.model.entity.Cliente;
import br.com.jdsb.clienteapi.repository.ClienteRepository;
import br.com.jdsb.clienteapi.service.ClienteService;
import br.com.jdsb.clienteapi.service.exceptions.ClienteJaCadastradoException;
import br.com.jdsb.clienteapi.service.exceptions.ClienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    private final ModelMapper mapper;

    @Override
    public Cliente findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ClienteNaoEncontradoException("Cliente Não Encontrado!"));
    }

    @Override
    public Cliente create(ClienteDTO dto) {
        if(repository.findByCpf(dto.getCpf()).isPresent())
            throw new ClienteJaCadastradoException("Cliente Ja Cadastrado");
        Cliente cliente = mapper.map(dto,Cliente.class);
        return repository.save(cliente);
    }

    @Override
    public Cliente findByCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(()-> new ClienteNaoEncontradoException("Cliente Não Encontrado!"));
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente update(ClienteDTO dto) {
        return repository.save(mapper.map(dto,Cliente.class));
    }

    @Override
    public void detele(Long id) {
      Cliente cliente = findById(id);
      repository.delete(cliente);
    }
}
