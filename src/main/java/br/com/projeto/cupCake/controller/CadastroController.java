package br.com.projeto.cupCake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroController {

    @GetMapping("/cadastrar")
    public ModelAndView cadastro() {
        return new ModelAndView("cadastro");
    }

}
