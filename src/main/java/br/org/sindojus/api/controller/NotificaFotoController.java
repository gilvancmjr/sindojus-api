package br.org.sindojus.api.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.sindojus.api.dto.FotoNoticiaDto;
import br.org.sindojus.api.dto.form.NoticiaFotoForm;
import br.org.sindojus.domain.exception.EntidadeNaoEncontradaException;
import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.service.CatalogoFotoProdutoService;
import br.org.sindojus.domain.service.FotoStorageSevice;
import br.org.sindojus.domain.service.NoticiaService;

@RestController
@RequestMapping("/noticias/{noticiaId}/foto")
public class NotificaFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private NoticiaService noticiaService;

    @Autowired
	private FotoStorageSevice fotoStorage;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoNoticiaDto atualizarFoto(@PathVariable Long noticiaId,NoticiaFotoForm noticiaFotoForm)throws IOException{

        Noticia noticia = noticiaService.buscarNoticia(noticiaId);

        FotoNoticia fotoNoticia =  new FotoNoticia();
        fotoNoticia.setNoticia(noticia);
        fotoNoticia.setContentType(noticiaFotoForm.getArquivo().getContentType());
        fotoNoticia.setTamanho(noticiaFotoForm.getArquivo().getSize());
        fotoNoticia.setNomeArquivo(noticiaFotoForm.getArquivo().getOriginalFilename());

        FotoNoticia fotoNoticiaAtual = catalogoFotoProdutoService.salvar(fotoNoticia,noticiaFotoForm.getArquivo().getInputStream()) ;
        FotoNoticiaDto fotoNoticiaDto = new FotoNoticiaDto(fotoNoticiaAtual);
        return fotoNoticiaDto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoNoticiaDto buscar(@PathVariable Long noticiaId) {
    FotoNoticia fotoNoticia = catalogoFotoProdutoService.buscarOuFalhar(noticiaId);
    FotoNoticiaDto fotoNoticiaDto = new FotoNoticiaDto(fotoNoticia);
    return fotoNoticiaDto;
    }
    
    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long noticiaId) {
		try {
            FotoNoticia fotoNoticia = catalogoFotoProdutoService.buscarOuFalhar(noticiaId);
			
			InputStream inputStream = fotoStorage.recuperar(fotoNoticia.getNomeArquivo(), "noticia");
			
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(inputStream));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
