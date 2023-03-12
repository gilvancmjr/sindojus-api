package br.org.sindojus.domain.repository;

import br.org.sindojus.domain.model.FotoNoticia;

public interface NoticiaRepositoryQueries {
    FotoNoticia save(FotoNoticia foto);
    void delete(FotoNoticia foto);
}
