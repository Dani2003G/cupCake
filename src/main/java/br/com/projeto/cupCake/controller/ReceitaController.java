package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.service.CupCakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/receita")
public class ReceitaController {

    private final CupCakeService cupCakeService;

    @GetMapping
    public ModelAndView receita() {
        return new ModelAndView("/usuario/receita");
    }

    @PostMapping("/novo")
    public ModelAndView novoReceita(CupCakeDTO dto) {
        cupCakeService.salvar(dto);
        return new ModelAndView("redirect:/home");
    }

}
