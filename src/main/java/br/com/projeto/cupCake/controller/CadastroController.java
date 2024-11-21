package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CadastroUsuarioDTO;
import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import br.com.projeto.cupCake.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cadastrar")
public class CadastroController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ModelAndView cadastro(CadastroUsuarioDTO dto) {
        return new ModelAndView("cadastro");
    }

    @PostMapping("/novo")
    public ModelAndView cadastrar(@Valid CadastroUsuarioDTO dto, BindingResult result) {
        usuarioService.validarCadastro(dto, result);

        if(result.hasErrors()) {
            return new ModelAndView("cadastro");
        }

        usuarioService.salvar(dto);
        return new ModelAndView("redirect:/login");
    }
}
