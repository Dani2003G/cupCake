package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.AlterarDadosDTO;
import br.com.projeto.cupCake.dto.AlterarSenhaDTO;
import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.service.CupCakeService;
import br.com.projeto.cupCake.service.FavoritoService;
import br.com.projeto.cupCake.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    private final UsuarioService usuarioService;

    @GetMapping
    public ModelAndView perfil() {
        return new ModelAndView("/usuario/perfil");
    }

    @GetMapping("/adicionarReceita")
    public ModelAndView cadastrarReceita(CupCakeDTO cupCakeDTO) {
        return new ModelAndView("/usuario/adicionarReceita");
    }

    @GetMapping("/minhasReceitas")
    public ModelAndView minhasReceitas(Principal principal) {
        ModelAndView mv = new ModelAndView("/usuario/minhasReceitas");
        List<CupCakeDTO> cupCakeDTOS = cupCakeService.buscaPorUsuario(principal.getName());
        mv.addObject("cupCakes", cupCakeDTOS);
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

    @GetMapping("/deletarConta")
    public ModelAndView deletarConta(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        usuarioService.deletarUsuario(principal.getName());
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/alterarDados")
    public ModelAndView alterarDados(Principal principal) {
        ModelAndView mv = new ModelAndView("/usuario/alterarDados");
        UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("usuarioDTO", usuarioDTO);
        return mv;
    }

    @PostMapping("/alterarDados")
    public ModelAndView alterarDados(AlterarDadosDTO dto, Principal principal) {
        usuarioService.alterarDados(dto, principal.getName());
        return new ModelAndView("redirect:/usuario");
    }

    @GetMapping("/alterarSenha")
    public ModelAndView alterarSenha(AlterarSenhaDTO dto) {
        return new ModelAndView("/usuario/alterarSenha");
    }

    @PostMapping("/alterarSenha")
    public ModelAndView alterarSenha(@Valid AlterarSenhaDTO dto, BindingResult result, Principal principal) {
        usuarioService.alterarSenha(dto, principal.getName(), result);
        if(result.hasErrors()) {
            return new ModelAndView("/usuario/alterarSenha");
        }
        return new ModelAndView("redirect:/usuario");
    }
}
