package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.CartaoDTO;
import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.dto.HistoricoPedidoDTO;
import br.com.projeto.cupCake.model.CupCake;
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

    private final HistoricoPedidoService historicoPedidoService;

    private final CartaoService cartaoService;

    private final CupCakeService cupCakeService;

    private final FavoritoService favoritoService;

    private final CarrinhoService carrinhoService;

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

    @GetMapping("/adicionarCarrinho/{id}")
    public ModelAndView adicionarCarrinho(@PathVariable Long id, Principal principal) {
        carrinhoService.adicionarCarrinho(id, principal.getName());
        return new ModelAndView("redirect:/receita/informacoes/" + id);
    }

    @GetMapping("/removerCarrinho/{id}")
    public ModelAndView removerCarrinho(@PathVariable Long id, Principal principal) {
        carrinhoService.removerCarrinho(id, principal.getName());
        return new ModelAndView("redirect:/receita/informacoes/" + id);
    }
}
