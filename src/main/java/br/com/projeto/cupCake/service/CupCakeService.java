package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.enums.Tipo;
import br.com.projeto.cupCake.model.CupCake;
import br.com.projeto.cupCake.repository.CupCakeRepository;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupCakeService {

    private final CupCakeRepository cupCakeRepository;

    private final UsuarioRepositoy usuarioRepositoy;

    public List<CupCakeDTO> buscarTudo() {
        List<CupCake> cupCakes = cupCakeRepository.findAll();
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }

    public void salvar(CupCakeDTO dto, String email) {
        CupCake cupCake = dto.toEntity();
        cupCake.setTipo(Tipo.RECEITA);
        cupCake.setUsuario(usuarioRepositoy.findByEmail(email));
        cupCakeRepository.save(cupCake);
    }

    public List<CupCakeDTO> buscaPorUsuario(String email) {
        List<CupCake> cupCakes = cupCakeRepository.buscarPorUsuario(email);
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }

    public void adiconarProduto(CupCakeDTO dto) {
        CupCake cupCake = dto.toEntity();
        cupCake.setTipo(Tipo.PRODUTO);
        cupCakeRepository.save(cupCake);
    }

    public List<CupCakeDTO> buscarTodosProdutos() {
        List<CupCake> cupCakes = cupCakeRepository.buscarTodosProdutos(Tipo.PRODUTO);
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }
}
