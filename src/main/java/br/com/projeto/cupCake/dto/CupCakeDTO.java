package br.com.projeto.cupCake.dto;

import br.com.projeto.cupCake.model.CupCake;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupCakeDTO {

    private String nome;

    private String descricao;

    private Integer tempoPreparo;

    private String igredientes;

    private String modoPreparo;

    private String urlImagem;

    public CupCakeDTO toDTO(CupCake cupCake) {
        CupCakeDTO dto = new CupCakeDTO();
        dto.setNome(cupCake.getNome());
        dto.setDescricao(cupCake.getDescricao());
        dto.setTempoPreparo(cupCake.getTempoPreparo());
        dto.setIgredientes(cupCake.getIgredientes());
        dto.setModoPreparo(cupCake.getModoPreparo());
        dto.setUrlImagem(cupCake.getUrlImagem());
        return dto;
    }

    public CupCake toEntity() {
        CupCake cupCake = new CupCake();
        cupCake.setNome(this.nome);
        cupCake.setDescricao(this.descricao);
        cupCake.setTempoPreparo(this.tempoPreparo);
        cupCake.setIgredientes(this.igredientes);
        cupCake.setModoPreparo(this.modoPreparo);
        cupCake.setUrlImagem(this.urlImagem);
        return cupCake;
    }
}
