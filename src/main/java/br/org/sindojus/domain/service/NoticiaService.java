package br.org.sindojus.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sindojus.api.dto.form.NoticiaForm;
import br.org.sindojus.domain.exception.NoticiaNaoEncontrada;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.repository.NoticiaRepository;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public List<Noticia> list() {
		return noticiaRepository.findAll();

	}
    public Noticia buscarNoticia(Long noticiaId) {
		return buscarOuFalhar(noticiaId);
	}
    public Noticia criaNoticia(NoticiaForm noticiaForm){
		Noticia noticia = new Noticia();
		noticia.setTitulo(noticiaForm.getTitulo());
		noticia.setDescricao(noticiaForm.getDescricao());
		return noticiaRepository.save(noticia);
	}
    public Noticia atualizarNoticia(Long noticiaId, NoticiaForm noticiaForm) {
     
		Noticia noticiaExistente = buscarOuFalhar(noticiaId);
		noticiaExistente.setTitulo(noticiaForm.getTitulo());
		noticiaExistente.setDescricao(noticiaForm.getDescricao());
	
		return noticiaRepository.save(noticiaExistente);
	}
    public void apagaNoticia(Long noticiaId) {
		buscarOuFalhar(noticiaId);
		noticiaRepository.deleteById(noticiaId);
	}
    private Noticia buscarOuFalhar(Long noticiaId) {
		return noticiaRepository.findById(noticiaId)
			.orElseThrow(() -> new NoticiaNaoEncontrada(noticiaId));
	}
 

    
}
