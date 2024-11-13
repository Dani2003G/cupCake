package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.model.CupCake;
import br.com.projeto.cupCake.repository.CupCakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupCakeService {

    private final CupCakeRepository cupCakeRepository;

    public List<CupCakeDTO> buscarTudo() {
        List<CupCake> cupCakes = cupCakeRepository.findAll();
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }

    public void salvar(CupCakeDTO dto) {
        cupCakeRepository.save(dto.toEntity());
    }
}
