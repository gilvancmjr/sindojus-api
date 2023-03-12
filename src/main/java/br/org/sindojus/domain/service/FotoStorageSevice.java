package br.org.sindojus.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageSevice {

    void armazenar(NovaFoto novaFoto);

    default String gerarNomeArquivo(String nomeOriginal){
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}

    InputStream recuperar(String nomeArquivo);


    @Builder
    @Getter
    class NovaFoto{

        private String nomeArquivo;
        private InputStream inputStream;

    }
    
}
