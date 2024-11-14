package br.com.projeto.cupCake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping
    public ModelAndView perfil() {
        return new ModelAndView("/usuario/perfil");
    }

}
