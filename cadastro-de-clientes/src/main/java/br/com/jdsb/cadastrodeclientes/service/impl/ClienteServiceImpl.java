package br.com.jdsb.cadastrodeclientes.service.impl;

import br.com.jdsb.cadastrodeclientes.feign.CepFeign;
import br.com.jdsb.cadastrodeclientes.feign.ClienteFeign;
import br.com.jdsb.cadastrodeclientes.feign.EnderecoFeign;
import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import br.com.jdsb.cadastrodeclientes.model.dto.EnderecoDTO;
import br.com.jdsb.cadastrodeclientes.service.ClienteService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteFeign clienteFeign;
    private final EnderecoFeign enderecoFeign;

    private final CepFeign cepFeign;

    @Override
    public void create(ClienteDTO dto) {
        EnderecoDTO endereco = enderecoFeign.create(dto.getEndereco()).getBody();
        dto.setEndereco(endereco);
        try {
            String cpf = dto.getCpf().replace(".","").replace("-","");
            dto.setCpf(cpf);
            clienteFeign.create(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<ClienteDTO> findAll() {
        return clienteFeign.getClientes().getBody();
    }

    @Override
    public ClienteDTO findById(Long id) {
        return clienteFeign.findById(id).getBody();
    }

    @Override
    public void update(ClienteDTO dto) {
        String cpf = dto.getCpf().replace(".","").replace("-","");
        dto.setCpf(cpf);
        enderecoFeign.update(dto.getEndereco().getId(),dto.getEndereco());
        clienteFeign.update(dto.getId(),dto);
    }

    public boolean validaCep(ClienteDTO dto, RedirectAttributes redirec){
        boolean cepInvalido = false;
        try {
            String cepEndereco = dto.getEndereco().getCep().replace("-","");
            EnderecoDTO endereco = cepFeign.findByCep(cepEndereco).getBody();
            endereco.setComplemento(dto.getEndereco().getComplemento());
            endereco.setId(dto.getEndereco().getId());
            dto.setEndereco(endereco);
        }catch (FeignException.NotFound ex){
            redirec.addFlashAttribute("fail", "CEP não encontrado!");
            cepInvalido = true;
        }
        return cepInvalido;
    }

    @Override
    public boolean clienteCadastrado(ClienteDTO dto, RedirectAttributes redirec) {
        boolean jaCadastrado = false;
        try {
            String cpf = dto.getCpf().replace(".","").replace("-","");
            ResponseEntity<ClienteDTO> entidade = clienteFeign
                    .findByCpf(cpf);
            HttpStatus status = entidade.getStatusCode();
            if (status.equals(HttpStatus.OK)) {
                redirec.addFlashAttribute("fail", "Cliente Já Cadastrado!");
                jaCadastrado = true;
            }
        }catch (FeignException.NotFound ex){
            jaCadastrado = false;
        }
        return jaCadastrado;
    }

    @Override
    public void deleteById(Long id) {
       clienteFeign.deleteById(id);
    }

    public ClienteDTO findByCpf(String cpf){
        return clienteFeign.findByCpf(cpf).getBody();
    }
}
