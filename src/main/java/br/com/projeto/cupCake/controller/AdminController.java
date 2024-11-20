package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.model.CupCake;
import br.com.projeto.cupCake.service.CupCakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final CupCakeService cupCakeService;

    @GetMapping
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("admin/home");
        List<CupCakeDTO> cupCakes = cupCakeService.buscarReceitasNaoAprovadas();
        mv.addObject("cupCakes", cupCakes);
        return mv;
    }

    @GetMapping("/aprovar/{id}")
    public ModelAndView aprovar(@PathVariable Long id) {
        cupCakeService.aprovar(id);
        return new ModelAndView("redirect:/admin");
    }

}
