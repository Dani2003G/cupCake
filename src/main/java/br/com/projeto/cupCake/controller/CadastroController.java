package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CadastroController {

    private final UsuarioService usuarioService;

    @GetMapping("/cadastrar")
    public ModelAndView cadastro(UsuarioDTO dto) {
        return new ModelAndView("cadastro");
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(UsuarioDTO dto) {
        usuarioService.salvar(dto);
        return new ModelAndView("cadastro");
    }
}
