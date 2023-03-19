package br.org.sindojus.api.dto;

import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.FotoParceiro;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.model.Parceiro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoParceiroDto {

    private Long id;
    private String nomeArquivo;
    private String contentType;
	private Long tamanho;
    private Parceiro parceiro;

    public FotoParceiroDto(FotoParceiro parceiro){
        this.id = parceiro.getId();
        this.nomeArquivo = parceiro.getNomeArquivo();
        this.contentType = parceiro.getContentType();
        this.tamanho = parceiro.getTamanho();
        this.parceiro = parceiro.getParceiro();
    }

}
