package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.service.CupCakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/receita")
public class ReceitaController {

    private final CupCakeService cupCakeService;

    @PostMapping("/novo")
    public ModelAndView novaReceita(CupCakeDTO dto, Principal principal) {
        cupCakeService.salvar(dto, principal.getName());
        return new ModelAndView("redirect:/home");
    }

}
