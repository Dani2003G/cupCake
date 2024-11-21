package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CadastroUsuarioDTO;
import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.dto.UsuarioDTO;
import br.com.projeto.cupCake.service.CupCakeService;
import br.com.projeto.cupCake.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final CupCakeService cupCakeService;

    private final UsuarioService usuarioService;

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

    @GetMapping("/receitas")
    public ModelAndView receitas() {
        ModelAndView mv = new ModelAndView("admin/receitas");
        List<CupCakeDTO> cupCakes = cupCakeService.buscarReceitasNaoAprovadas();
        mv.addObject("cupCakes", cupCakes);
        return mv;
    }

    @GetMapping("/usuarios")
    public ModelAndView usuarios() {
        ModelAndView mv = new ModelAndView("admin/usuarios");
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosComuns();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/deletarUsuario/{id}")
    public ModelAndView deletarUsuario(@PathVariable Long id) {
        usuarioService.validarDelecaoUsuario(id);
        return new ModelAndView("redirect:/admin/usuarios");
    }

    @GetMapping("/administradores")
    public ModelAndView administradores(@RequestParam(required = false) String mensagemErro) {
        ModelAndView mv = new ModelAndView("admin/administradores");
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosAdministradores();
        mv.addObject("usuarios", usuarios);
        mv.addObject("mensagemErro", mensagemErro);
        return mv;
    }

    @GetMapping("/deletarAdm/{id}")
    public ModelAndView deletarAdm(@PathVariable Long id, Principal principal) {
        ModelAndView mv = new ModelAndView("redirect:/admin/administradores");
        usuarioService.validarDelecaoAdm(id, principal.getName(), mv);
        return mv;
    }

    @GetMapping("/verUsuario/{id}")
    public ModelAndView verUsuario(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("admin/verUsuario");
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
        mv.addObject("usuario", usuarioDTO);
        return mv;
    }

    @GetMapping("/adicionarNovoAdmin")
    public ModelAndView adicionarNovoAdmin(CadastroUsuarioDTO dto) {
        return new ModelAndView("admin/adicionarNovoAdmin");
    }

    @PostMapping("/adicionarNovoAdmin")
    public ModelAndView adicionarNovoAdmin(@Valid CadastroUsuarioDTO dto, BindingResult result) {
        usuarioService.validarCadastro(dto, result);

        if(result.hasErrors()) {
            return new ModelAndView("admin/adicionarNovoAdmin");
        }
        usuarioService.salvarAdmin(dto);
        return new ModelAndView("redirect:/admin/administradores");
    }
}
