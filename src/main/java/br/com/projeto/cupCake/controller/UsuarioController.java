package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final CupCakeService cupCakeService;

    private final FavoritoService favoritoService;

    @GetMapping
    public ModelAndView perfil() {
        return new ModelAndView("/usuario/perfil");
    }

    @GetMapping("/adicionarReceita")
    public ModelAndView cadastrarReceita() {
        return new ModelAndView("/usuario/adicionarReceita");
    }

    @GetMapping("/receitas")
    public ModelAndView receitas(Principal principal) {
        ModelAndView mv = new ModelAndView("/usuario/receitas");
        List<CupCakeDTO> cupCakeDTOS = cupCakeService.buscaPorUsuario(principal.getName());
        mv.addObject("receitas", cupCakeDTOS);
        return mv;
    }

    @GetMapping("/favoritos")
    public ModelAndView favoritos(Principal principal) {
        ModelAndView mv = new ModelAndView("/usuario/favoritos");
        List<CupCakeDTO> cupCakes = cupCakeService.buscarFavoritosPorUsuario(principal.getName());
        mv.addObject("cupCakes", cupCakes);
        return mv;
    }

    @GetMapping("/receita/desfavoritar/{id}")
    public ModelAndView desfavoritar(@PathVariable Long id, Principal principal) {
        favoritoService.desfavoritar(id, principal.getName());
        return new ModelAndView("/usuario/favoritos");
    }
}
