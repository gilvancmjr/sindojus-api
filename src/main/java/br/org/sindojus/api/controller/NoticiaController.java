package br.org.sindojus.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.sindojus.api.dto.NoticiaDto;
import br.org.sindojus.api.dto.form.NoticiaForm;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.service.NoticiaService;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public ResponseEntity<List<NoticiaDto>> listar() {
        List<Noticia> noticia = noticiaService.list();
        List<NoticiaDto> dto = NoticiaDto.toCollectionModel(noticia);
		return ResponseEntity.ok(dto);
	}
    @GetMapping("/{noticiaId}")
	public ResponseEntity<NoticiaDto> buscarNoticia(@PathVariable Long noticiaId) {
		Noticia noticia = noticiaService.buscarNoticia(noticiaId);
		NoticiaDto noticiaDto = new NoticiaDto(noticia);
		return ResponseEntity.ok(noticiaDto);
	}
    @PostMapping()
	public ResponseEntity<NoticiaDto> criaNoticia(@RequestBody NoticiaForm noticiaForm){
		Noticia noticia = noticiaService.criaNoticia(noticiaForm) ;
        NoticiaDto noticiaDto = new NoticiaDto(noticia);
		return ResponseEntity.ok().body(noticiaDto);
	}
    @PutMapping(value = "/{noticiaId}")
	public ResponseEntity<NoticiaDto> atualizaNoticia(@PathVariable Long noticiaId,@RequestBody NoticiaForm noticiaForm){
		Noticia noticia = noticiaService.atualizarNoticia(noticiaId,noticiaForm);

        NoticiaDto noticiaDto = new NoticiaDto(noticia);
		return ResponseEntity.ok().body(noticiaDto);
	}
    @DeleteMapping("/{noticiaId}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Long noticiaId){
         noticiaService.apagaNoticia(noticiaId);
         return ResponseEntity.noContent().build();
    }
    
}
