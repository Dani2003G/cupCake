package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.service.CupCakeService;
import br.com.projeto.cupCake.service.FavoritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/receita")
public class ReceitaController {

    private final CupCakeService cupCakeService;

    private final FavoritoService favoritoService;

    @PostMapping("/novo")
    public ModelAndView novaReceita(@Valid CupCakeDTO dto, BindingResult result , Principal principal) {
        try {
            Integer.parseInt(dto.getTempoPreparo());
        } catch (Exception e) {
            if(!result.hasFieldErrors("tempoPreparo")) {
                result.addError(new FieldError("dto", "tempoPreparo", "Tempo preparo deve ser numérico"));
            }
        }

        if(result.hasErrors()) {
            return new ModelAndView("usuario/adicionarReceita");
        }
        cupCakeService.salvar(dto, principal.getName());
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/informacoes/{id}")
    public ModelAndView informacoes(@PathVariable Long id, CupCakeDTO dto, Principal principal) {
        CupCakeDTO cupCakeDTO = cupCakeService.buscarPorId(id);
        Boolean isFavorito = false;
        if(Objects.nonNull(principal)) {
            isFavorito = favoritoService.isFavorito(id, principal.getName());
        }
        ModelAndView mv = new ModelAndView("informacoesReceita");
        mv.addObject("cupCake", cupCakeDTO);
        mv.addObject("isFavorito", isFavorito);
        return mv;
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

    @GetMapping("/deletarReceita/{id}")
    public ModelAndView deletarReceita(@PathVariable Long id) {
        cupCakeService.deletar(id);
        return new ModelAndView("redirect:/usuario/minhasReceitas");
    }
}
