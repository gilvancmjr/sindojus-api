package br.org.sindojus.api.controller;

import br.org.sindojus.api.dto.NoticiaDto;
import br.org.sindojus.api.dto.ParceiroDto;
import br.org.sindojus.api.dto.form.NoticiaForm;
import br.org.sindojus.api.dto.form.ParceiroForm;
import br.org.sindojus.domain.exception.EntidadeNaoEncontradaException;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.model.Parceiro;
import br.org.sindojus.domain.service.FotoStorageSevice;
import br.org.sindojus.domain.service.NoticiaService;
import br.org.sindojus.domain.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    @Autowired
    private ParceiroService parceiroService;
    @Autowired
    private FotoStorageSevice fotoStorage;

    @GetMapping
    public ResponseEntity<List<ParceiroDto>> listar() {
        List<Parceiro> parceiros = parceiroService.list();
        List<ParceiroDto> dto = ParceiroDto.toCollectionModel(parceiros);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{parceiroId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParceiroDto> buscarParceiro(@PathVariable Long parceiroId) {
        Parceiro parceiro = parceiroService.buscarParceiro(parceiroId);
        ParceiroDto parceiroDto = new ParceiroDto(parceiro);
        return ResponseEntity.ok(parceiroDto);
    }

    @GetMapping(value = "/{parceiroId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> buscarFotoParceiro(@PathVariable Long parceiroId) {

        try {
            Parceiro parceiro = parceiroService.buscarParceiro(parceiroId);
            InputStream inputStream = fotoStorage.recuperar(parceiro.getNomeArquivo(), "noticia");

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ParceiroDto> criaParceiro(ParceiroForm parceiroForm) throws IOException {
        Parceiro parceiro = parceiroService.criaParceiro(parceiroForm, parceiroForm.getArquivo().getInputStream());
        ParceiroDto parceiroDto = new ParceiroDto(parceiro);
        return ResponseEntity.ok().body(parceiroDto);
    }

    @PutMapping(value = "/{parceiroId}")
    public ResponseEntity<ParceiroDto> atualizaParceiro(@PathVariable Long parceiroId, @RequestBody ParceiroForm parceiroForm) {
        Parceiro parceiro = parceiroService.atualizarParceiro(parceiroId, parceiroForm);

        ParceiroDto parceiroDto = new ParceiroDto(parceiro);
        return ResponseEntity.ok().body(parceiroDto);
    }

    @DeleteMapping("/{parceiroId}")
    public ResponseEntity<Void> deleteParceiro(@PathVariable Long parceiroId) {
        parceiroService.apagaParceiro(parceiroId);
        return ResponseEntity.noContent().build();
    }

}
