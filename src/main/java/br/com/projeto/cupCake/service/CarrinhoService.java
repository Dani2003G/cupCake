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

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

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
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(usuario.getId());
        if (carrinhoOptional.isPresent()) {
            Carrinho carrinho = carrinhoOptional.get();
            if (Objects.isNull(carrinho.getCupCake())) {
                carrinho.setCupCake(new ArrayList<>());
                carrinho.getCupCake().add(cupCake);
            } else {
                Boolean tem = false;
                for (CupCake cake : carrinho.getCupCake()) {
                    if (cake.getId().equals(cupCake.getId())) {
                        tem = true;
                        break;
                    }
                }
                if (!tem) {
                    carrinho.getCupCake().add(cupCake);
                }
            }
            carrinhoRepository.save(carrinho);
        } else {
            Carrinho carrinho = new Carrinho();
            carrinho.setId(id);
            carrinho.setCupCake(new ArrayList<>());
            carrinho.getCupCake().add(cupCake);
            carrinhoRepository.save(carrinho);
        }
    }
}
