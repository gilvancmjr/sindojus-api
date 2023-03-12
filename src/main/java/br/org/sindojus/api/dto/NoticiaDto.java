package br.org.sindojus.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.org.sindojus.domain.model.Noticia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticiaDto {

    private Long id;
    private String titulo;
    private String descricao;

    public NoticiaDto(Noticia noticia){
        this.id = noticia.getId();
        this.titulo = noticia.getTitulo();
        this.descricao = noticia.getDescricao();
      
    }

    public static List<NoticiaDto> toCollectionModel(List<Noticia> noticias) {
		return noticias.stream().map(noticia -> new NoticiaDto(noticia))
				.collect(Collectors.toList());
	}
    
}
