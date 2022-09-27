package br.com.jdsb.cadastrodeclientes.controller;

import br.com.jdsb.cadastrodeclientes.model.dto.ClienteDTO;
import br.com.jdsb.cadastrodeclientes.service.ClienteService;
import br.com.jdsb.cadastrodeclientes.validators.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ClienteValidator());
    }

    @GetMapping("/cadastrar")
    public String cadastrar(ClienteDTO funcionario) {
        return "cliente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("clientes", service.findAll());
        return "cliente/lista";
    }

    @PostMapping("/cep")
    public String cep(ClienteDTO cliente, BindingResult result, RedirectAttributes attr) {
        attr.addFlashAttribute("clienteDTO",cliente);
        if(service.validaCep(cliente,attr)){
            return "redirect:/clientes/cadastrar";
        }
        return "redirect:/clientes/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid ClienteDTO cliente, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }

        if(service.validaCep(cliente,attr)){
            return "redirect:/clientes/cadastrar";
        }else if(service.clienteCadastrado(cliente,attr)){
            return "redirect:/clientes/cadastrar";
        }else {
            service.create(cliente);
            attr.addFlashAttribute("success", "Cliente inserido com sucesso.");
            return "redirect:/clientes/cadastrar";
        }
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("clienteDTO", service.findById(id));
        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(ClienteDTO cliente, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        if(service.validaCep(cliente,attr)) {
            return "redirect:/clientes/cadastrar";
        }else {
            service.update(cliente);
            attr.addFlashAttribute("success", "Cliente editado com sucesso.");
            return "redirect:/clientes/cadastrar";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.deleteById(id);
        attr.addFlashAttribute("success", "Cliente removido com sucesso.");
        return "redirect:/clientes/listar";
    }



}
