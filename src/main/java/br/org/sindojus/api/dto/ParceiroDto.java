package br.org.sindojus.api.dto;

import br.org.sindojus.domain.model.Parceiro;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ParceiroDto {

    private Long id;
    private String titulo;
    private String descricao;

    public ParceiroDto(Parceiro parceiro){
        this.id = parceiro.getId();
        this.titulo = parceiro.getTitulo();
        this.descricao = parceiro.getDescricao();
      
    }

    public static List<ParceiroDto> toCollectionModel(List<Parceiro> parceiros) {
		return parceiros.stream().map(parceiro -> new ParceiroDto(parceiro))
				.collect(Collectors.toList());
	}
    
}
