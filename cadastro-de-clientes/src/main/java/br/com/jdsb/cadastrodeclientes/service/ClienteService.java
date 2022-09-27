package br.com.jdsb.cadastrodeclientes.service;

import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface ClienteService {

  public void create(ClienteDTO dto);
  public List<ClienteDTO> findAll();
  public ClienteDTO findById(Long id);
  public void update(ClienteDTO dto);
  public boolean clienteCadastrado(ClienteDTO dto, RedirectAttributes redirec);

  public boolean validaCep(ClienteDTO dto, RedirectAttributes redirec);

  public void deleteById(Long id);

}
