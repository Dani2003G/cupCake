package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CartaoDTO;
import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.dto.HistoricoPedidoDTO;
import br.com.projeto.cupCake.service.CartaoService;
import br.com.projeto.cupCake.service.CupCakeService;
import br.com.projeto.cupCake.service.HistoricoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final HistoricoPedidoService historicoPedidoService;

    private final CartaoService cartaoService;

    private final CupCakeService cupCakeService;

    @GetMapping
    public ModelAndView perfil() {
        return new ModelAndView("/usuario/perfil");
    }

    @GetMapping("/adicionarReceita")
    public ModelAndView cadastrarReceita() {
        return new ModelAndView("/usuario/adicionarReceita");
    }

    @GetMapping("/historicoPedido")
    public ModelAndView historicoPedido(Principal principal) {
        ModelAndView mv = new ModelAndView("/usuario/historicoPedido");
        List<HistoricoPedidoDTO> historicoPedidos = historicoPedidoService.buscarTudoPorUsuario(principal.getName());
        mv.addObject("historicoPedidos", historicoPedidos);
        return mv;
    }

    @GetMapping("/receitas")
    public ModelAndView receitas(Principal principal) {
        ModelAndView mv = new ModelAndView("/usuario/receitas");
        List<CupCakeDTO> cupCakeDTOS = cupCakeService.buscaPorUsuario(principal.getName());
        mv.addObject("receitas", cupCakeDTOS);
        return mv;
    }

    @GetMapping("/cartao")
    public ModelAndView cartao() {
        return new ModelAndView("/usuario/cartao");
    }

    @PostMapping("/cartao/novo")
    public ModelAndView cartao(CartaoDTO dto) {
        cartaoService.salvar(dto);
        return new ModelAndView("redirect:/usuario");
    }

}
