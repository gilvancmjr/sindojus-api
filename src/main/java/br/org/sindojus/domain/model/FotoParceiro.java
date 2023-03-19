package br.org.sindojus.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoParceiro {
    @EqualsAndHashCode.Include
    @Id
    @Column(name ="parceiro_id")
    private Long id;
    private String nomeArquivo;
    private String contentType;
    private Long tamanho;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Parceiro parceiro;
}
