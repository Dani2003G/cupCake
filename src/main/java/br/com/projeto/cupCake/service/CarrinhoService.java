package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.model.Carrinho;
import br.com.projeto.cupCake.model.CupCake;
import br.com.projeto.cupCake.model.Usuario;
import br.com.projeto.cupCake.repository.CarrinhoRepository;
import br.com.projeto.cupCake.repository.CupCakeRepository;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarrinhoService {

    private final CupCakeRepository cupCakeRepository;

    private final UsuarioRepositoy usuarioRepositoy;

    private final CarrinhoRepository carrinhoRepository;

    public void adicionarCarrinho(Long id, String email) {
        CupCake cupCake = cupCakeRepository.findById(id).get();
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        List<Carrinho> listaCarrinho = carrinhoRepository.buscarPorCupCakeEUsuario(usuario.getId(), cupCake.getId());
        if (listaCarrinho.isEmpty()) {
            Carrinho carrinho = new Carrinho();
            carrinho.setIdCupCake(cupCake.getId());
            carrinho.setIdUsuario(usuario.getId());
            carrinhoRepository.save(carrinho);
        }
    }

    public Boolean isCarrinho(String email, Long id) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        CupCake cupCake = cupCakeRepository.findById(id).get();
        List<Carrinho> listaCarrinho = carrinhoRepository.buscarPorCupCakeEUsuario(usuario.getId(), cupCake.getId());
        if (listaCarrinho.isEmpty()) {
            return false;
        }
        return true;
    }

    public void removerCarrinho(Long id, String email) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        CupCake cupCake = cupCakeRepository.findById(id).get();
        List<Carrinho> listaCarrinho = carrinhoRepository.buscarPorCupCakeEUsuario(usuario.getId(), cupCake.getId());
        if(!listaCarrinho.isEmpty()){
            carrinhoRepository.deleteAll(listaCarrinho);
        }
    }
}
