package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.CartaoDTO;
import br.com.projeto.cupCake.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public void salvar(CartaoDTO dto) {
        cartaoRepository.save(dto.toEntity());
    }
}
