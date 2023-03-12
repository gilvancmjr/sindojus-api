package br.org.sindojus.api.dto;

import br.org.sindojus.domain.model.FotoNoticia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoNoticiaDto {

    private Long id;
    private String nomeArquivo;
    private String contentType;
	private Long tamanho;
    
    public FotoNoticiaDto(FotoNoticia fotoNoticia){
       this.nomeArquivo = fotoNoticia.getNomeArquivo();
       this.contentType = fotoNoticia.getContentType();
       this.tamanho = fotoNoticia.getTamanho();
    }
}
