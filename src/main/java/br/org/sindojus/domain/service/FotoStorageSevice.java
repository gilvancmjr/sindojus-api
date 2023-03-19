package br.org.sindojus.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageSevice {

    void armazenar(NovaFoto novaFoto, String tipoEntidade);

    default String gerarNomeArquivo(String nomeOriginal){
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }

    void remover(String nomeArquivo, String tipoEntidade);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto, String tipoEntidade) {
        this.armazenar(novaFoto, tipoEntidade);

        if (nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo,tipoEntidade);
        }
    }

    InputStream recuperar(String nomeArquivo, String tipoEntidade);


    @Builder
    @Getter
    class NovaFoto{

        private String nomeArquivo;
        private InputStream inputStream;

    }

}