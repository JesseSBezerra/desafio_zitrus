package br.com.jdsb.enderecoapi.service;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import br.com.jdsb.enderecoapi.model.entity.Endereco;

import java.util.List;

public interface EnderecoService {
    public Endereco findById(Long id);
    public Endereco create(EnderecoDTO dto);
    public Endereco update(EnderecoDTO dto);
    public List<Endereco> findAll();
    public void delete(Long id);
}
