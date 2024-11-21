package br.com.projeto.cupCake.dto;

import br.com.projeto.cupCake.model.CupCake;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupCakeDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotEmpty
    private String tempoPreparo;

    @NotBlank
    private String igredientes;

    @NotBlank
    private String modoPreparo;

    @NotBlank
    private String urlImagem;

    public CupCakeDTO toDTO(CupCake cupCake) {
        CupCakeDTO dto = new CupCakeDTO();
        dto.setId(cupCake.getId());
        dto.setNome(cupCake.getNome());
        dto.setDescricao(cupCake.getDescricao());
        dto.setTempoPreparo(cupCake.getTempoPreparo().toString());
        dto.setIgredientes(cupCake.getIgredientes());
        dto.setModoPreparo(cupCake.getModoPreparo());
        dto.setUrlImagem(cupCake.getUrlImagem());
        return dto;
    }

    public CupCake toEntity() {
        CupCake cupCake = new CupCake();
        cupCake.setNome(this.nome);
        cupCake.setDescricao(this.descricao);
        cupCake.setTempoPreparo(Integer.parseInt(this.tempoPreparo));
        cupCake.setIgredientes(this.igredientes);
        cupCake.setModoPreparo(this.modoPreparo);
        cupCake.setUrlImagem(this.urlImagem);
        return cupCake;
    }
}
