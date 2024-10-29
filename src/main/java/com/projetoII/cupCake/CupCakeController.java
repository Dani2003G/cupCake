package com.projetoII.cupCake;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CupCakeController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("index");
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        return new ModelAndView("cadastro");
    }

    @GetMapping("/buscaReceita")
    public ModelAndView buscaReceita() {
        return new ModelAndView("buscaReceita");
    }

}
