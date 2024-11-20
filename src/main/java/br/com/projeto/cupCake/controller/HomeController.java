package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.service.CupCakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final CupCakeService cupCakeService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        List<CupCakeDTO> cupCakes = cupCakeService.buscarTodasAprovadas();
        mv.addObject("cupCakes", cupCakes);
        return mv;
    }

}
