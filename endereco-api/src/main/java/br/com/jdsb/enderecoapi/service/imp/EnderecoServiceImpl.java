package br.com.jdsb.enderecoapi.service.imp;

import br.com.jdsb.enderecoapi.model.dto.EnderecoDTO;
import br.com.jdsb.enderecoapi.model.entity.Endereco;
import br.com.jdsb.enderecoapi.repository.EnderecoRepository;
import br.com.jdsb.enderecoapi.service.EnderecoService;
import br.com.jdsb.enderecoapi.service.exceptions.EnderecoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Endereco findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new EnderecoNaoEncontradoException("Endereco não encontrado!"));
    }

    @Override
    public Endereco create(EnderecoDTO dto) {
        return repository.save(mapper.map(dto,Endereco.class));
    }

    @Override
    public Endereco update(EnderecoDTO dto) {
        return repository.save(mapper.map(dto,Endereco.class));
    }

    @Override
    public List<Endereco> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
       Endereco endereco = findById(id);
       repository.delete(endereco);
    }
}
