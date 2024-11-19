package br.com.projeto.cupCake.service;

import br.com.projeto.cupCake.dto.CupCakeDTO;
import br.com.projeto.cupCake.model.CupCake;
import br.com.projeto.cupCake.model.Favorito;
import br.com.projeto.cupCake.model.FavoritoId;
import br.com.projeto.cupCake.model.Usuario;
import br.com.projeto.cupCake.repository.CupCakeRepository;
import br.com.projeto.cupCake.repository.FavoritoRepository;
import br.com.projeto.cupCake.repository.UsuarioRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final UsuarioRepositoy usuarioRepositoy;

    private final CupCakeRepository cupCakeRepository;

    private final FavoritoRepository favoritoRepository;

    public void favoritar(Long id, String email) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        CupCake cupCake = cupCakeRepository.findById(id).get();
        FavoritoId favoritoId = new FavoritoId();
        favoritoId.setIdUsuario(usuario.getId());
        favoritoId.setIdCupCake(cupCake.getId());
        Favorito favorito = new Favorito();
        favorito.setId(favoritoId);
        favoritoRepository.save(favorito);
    }

    public Boolean isFavorito(Long id, String email) {
        CupCake cupCake = cupCakeRepository.findById(id).get();
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        Optional<Favorito> favorito = favoritoRepository.findById(new FavoritoId(usuario.getId(), cupCake.getId()));
        return favorito.isPresent();
    }

    public void desfavoritar(Long id, String email) {
        Usuario usuario = usuarioRepositoy.findByEmail(email);
        CupCake cupCake = cupCakeRepository.findById(id).get();
        Optional <Favorito> favorito = favoritoRepository.findById(new FavoritoId(usuario.getId(), cupCake.getId()));
        favorito.ifPresent(favoritoRepository::delete);
    }
}
