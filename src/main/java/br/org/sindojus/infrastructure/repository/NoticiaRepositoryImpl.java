package br.org.sindojus.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.repository.NoticiaRepositoryQueries;

@Repository
public class NoticiaRepositoryImpl implements NoticiaRepositoryQueries{

    @PersistenceContext
	private EntityManager manager;

    @Transactional
	@Override
	public FotoNoticia save(FotoNoticia foto) {
		return manager.merge(foto);
	}

	@Override
	public void delete(FotoNoticia foto) {
		manager.remove(foto);
	}

    
}
