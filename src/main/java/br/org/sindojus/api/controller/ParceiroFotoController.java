package br.org.sindojus.api.controller;

import br.org.sindojus.api.dto.FotoNoticiaDto;
import br.org.sindojus.api.dto.FotoParceiroDto;
import br.org.sindojus.api.dto.form.NoticiaFotoForm;
import br.org.sindojus.api.dto.form.ParceiroForm;
import br.org.sindojus.domain.exception.EntidadeNaoEncontradaException;
import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.FotoParceiro;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.model.Parceiro;
import br.org.sindojus.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/parceiros/{parceiroId}/foto")
public class ParceiroFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private NoticiaService noticiaService;
    @Autowired
    private CatalogoFotoParceiroService catalogoFotoParceiroService;
    @Autowired
    private ParceiroService parceiroService;

    @Autowired
	private FotoStorageSevice fotoStorage;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoParceiroDto atualizarFoto(@PathVariable Long parceiroId, ParceiroForm parceiroForm)throws IOException{

        Parceiro parceiro = parceiroService.buscarParceiro(parceiroId);

        FotoParceiro fotoParceiro = new FotoParceiro();
        fotoParceiro.setParceiro(parceiro);
        fotoParceiro.setContentType(parceiroForm.getArquivo().getContentType());
        fotoParceiro.setTamanho(parceiroForm.getArquivo().getSize());
        fotoParceiro.setNomeArquivo(parceiroForm.getArquivo().getOriginalFilename());

        FotoParceiro fotoParceiroAtual = catalogoFotoParceiroService.salvar(fotoParceiro, parceiroForm.getArquivo().getInputStream());
        FotoParceiroDto parceiroDto = new FotoParceiroDto(fotoParceiroAtual);
        return parceiroDto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoNoticiaDto buscar(@PathVariable Long noticiaId) {
    FotoNoticia fotoNoticia = catalogoFotoProdutoService.buscarOuFalhar(noticiaId);
    FotoNoticiaDto fotoNoticiaDto = new FotoNoticiaDto(fotoNoticia);
    return fotoNoticiaDto;
    }
    
    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long parceiroId) {
		try {
            FotoNoticia fotoNoticia = catalogoFotoProdutoService.buscarOuFalhar(parceiroId);
			
			InputStream inputStream = fotoStorage.recuperar(fotoNoticia.getNomeArquivo(), "noticia");
			
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(inputStream));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
