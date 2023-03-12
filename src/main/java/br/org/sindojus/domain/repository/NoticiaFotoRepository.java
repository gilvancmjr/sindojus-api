package br.org.sindojus.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sindojus.domain.model.FotoNoticia;

public interface NoticiaFotoRepository  extends JpaRepository<FotoNoticia, Long>{

    Optional<FotoNoticia> findByNoticiaId(Long noticiaId);
    
}
