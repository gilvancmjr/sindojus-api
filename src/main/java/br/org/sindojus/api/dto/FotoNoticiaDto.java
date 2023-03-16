package br.org.sindojus.api.dto;

import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.Noticia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoNoticiaDto {

    private Long id;
    private String nomeArquivo;
    private String contentType;
	private Long tamanho;
    private Noticia noticia;
    
    public FotoNoticiaDto(FotoNoticia fotoNoticia){
        this.id = fotoNoticia.getId();
        this.nomeArquivo = fotoNoticia.getNomeArquivo();
        this.contentType = fotoNoticia.getContentType();
        this.tamanho = fotoNoticia.getTamanho();
        this.noticia = fotoNoticia.getNoticia();
    }
    
}
