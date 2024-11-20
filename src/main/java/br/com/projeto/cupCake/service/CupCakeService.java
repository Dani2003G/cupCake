package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.model.CupCake;
import br.com.projeto.cupCake.model.Favorito;
import br.com.projeto.cupCake.model.Usuario;
import br.com.projeto.cupCake.repository.CupCakeRepository;
import br.com.projeto.cupCake.repository.FavoritoRepository;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CupCakeService {

    private final CupCakeRepository cupCakeRepository;

    private final UsuarioRepositoy usuarioRepositoy;

    private final FavoritoRepository favoritoRepository;

    public List<CupCakeDTO> buscarTudo() {
        List<CupCake> cupCakes = cupCakeRepository.findAll();
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }

    public void salvar(CupCakeDTO dto, String email) {
        CupCake cupCake = dto.toEntity();
        cupCake.setUsuario(usuarioRepositoy.findByEmail(email));
        cupCakeRepository.save(cupCake);
    }

    public List<CupCakeDTO> buscaPorUsuario(String email) {
        List<CupCake> cupCakes = cupCakeRepository.buscarPorUsuario(email);
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }

    public CupCakeDTO buscarPorId(Long id) {
        Optional<CupCake> cupCake = cupCakeRepository.findById(id);
        return cupCake.map(cake -> new CupCakeDTO().toDTO(cake)).orElse(null);
    }

    public List<CupCakeDTO> buscarFavoritosPorUsuario(String email) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        List<Favorito> favoritos = favoritoRepository.buscarPorUsuario(usuario.getId());
        List<CupCake> cupCakes = cupCakeRepository.findAll();
        List<Long> idCupCakes = new ArrayList<>();
        for (Favorito favorito : favoritos) {
            idCupCakes.add(favorito.getId().getIdCupCake());
        }
        cupCakes = cupCakes.stream().filter(cupCake -> idCupCakes.contains(cupCake.getId())).toList();
        return cupCakes.stream().map(new CupCakeDTO()::toDTO).toList();
    }

    public void deletar(Long id) {
        cupCakeRepository.deleteById(id);
    }
}
