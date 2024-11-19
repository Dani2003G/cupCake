package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.model.Favorito;
import br.com.projeto.cupCake.service.CupCakeService;
import br.com.projeto.cupCake.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/receita")
public class ReceitaController {

    private final CupCakeService cupCakeService;

    private final FavoritoService favoritoService;

    @PostMapping("/novo")
    public ModelAndView novaReceita(CupCakeDTO dto, Principal principal) {
        cupCakeService.salvar(dto, principal.getName());
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/informacoes/{id}")
    public ModelAndView informacoes(@PathVariable Long id, CupCakeDTO dto, Principal principal) {
        CupCakeDTO cupCakeDTO = cupCakeService.buscarPorId(id);
        Boolean isFavorito = favoritoService.isFavorito(id, principal.getName());
        if (cupCakeDTO.getTipo().equals("Receita")) {
            ModelAndView mv = new ModelAndView("informacoesReceita");
            mv.addObject("cupCake", cupCakeDTO);
            mv.addObject("isFavorito", isFavorito);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("informacoesProduto");
            mv.addObject("cupCake", cupCakeDTO);
            return mv;
        }
    }

    @GetMapping("/favoritar/{id}")
    public ModelAndView favoritar(@PathVariable Long id, Principal principal) {
        favoritoService.favoritar(id, principal.getName());
        return new ModelAndView("redirect:/receita/informacoes/" + id);
    }

    @GetMapping("/desfavoritar/{id}")
    public ModelAndView desfavoritar(@PathVariable Long id, Principal principal) {
        favoritoService.desfavoritar(id, principal.getName());
        return new ModelAndView("redirect:/receita/informacoes/" + id);
    }
}
