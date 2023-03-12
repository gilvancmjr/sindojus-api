package br.org.sindojus.domain.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.Noticia;


public interface NoticiaRepository extends JpaRepository<Noticia, Long>, NoticiaRepositoryQueries {
    
  @Query("from FotoNoticia f where f.noticia.id = :noticiaId")  
  Optional<FotoNoticia> findFotoById(Long noticiaId);
}
