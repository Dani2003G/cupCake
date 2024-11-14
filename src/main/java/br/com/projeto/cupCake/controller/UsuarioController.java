package br.com.projeto.cupCake.controller;

import br.com.projeto.cupCake.dto.HistoricoPedidoDTO;
import br.com.projeto.cupCake.service.HistoricoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final HistoricoPedidoService historicoPedidoService;

    @GetMapping
    public ModelAndView perfil() {
        return new ModelAndView("/usuario/perfil");
    }

    @GetMapping("/historicoPedido")
    public ModelAndView historicoPedido() {
        ModelAndView mv = new ModelAndView("/usuario/historicoPedido");
        List<HistoricoPedidoDTO> historicoPedidos = historicoPedidoService.buscarTudo();
        mv.addObject("historicoPedidos", historicoPedidos);
        return mv;
    }

}
