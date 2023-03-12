package br.org.sindojus.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoNoticia {

    @EqualsAndHashCode.Include
	@Id
    @Column(name ="noticia_id")
    private Long id;
    private String nomeArquivo;
    private String contentType;
	private Long tamanho;
    @OneToOne(fetch = FetchType.LAZY)
	@MapsId
    private Noticia noticia;
    
}
