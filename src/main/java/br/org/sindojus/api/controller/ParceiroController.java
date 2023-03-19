package br.org.sindojus.api.controller;

import br.org.sindojus.api.dto.NoticiaDto;
import br.org.sindojus.api.dto.ParceiroDto;
import br.org.sindojus.api.dto.form.NoticiaForm;
import br.org.sindojus.api.dto.form.ParceiroForm;
import br.org.sindojus.domain.model.Noticia;
import br.org.sindojus.domain.model.Parceiro;
import br.org.sindojus.domain.service.NoticiaService;
import br.org.sindojus.domain.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    @Autowired
    private ParceiroService parceiroService;
    @GetMapping
    public ResponseEntity<List<ParceiroDto>> listar() {
        List<Parceiro> parceiros = parceiroService.list();
        List<ParceiroDto> dto = ParceiroDto.toCollectionModel(parceiros);
		return ResponseEntity.ok(dto);
	}
    @GetMapping("/{parceiroId}")
	public ResponseEntity<ParceiroDto> buscarParceiro(@PathVariable Long parceiroId) {
        Parceiro parceiro = parceiroService.buscarParceiro(parceiroId);
        ParceiroDto parceiroDto = new ParceiroDto(parceiro);
		return ResponseEntity.ok(parceiroDto);
	}
    @PostMapping()
	public ResponseEntity<ParceiroDto> criaParceiro(@RequestBody ParceiroForm parceiroForm){
        Parceiro parceiro = parceiroService.criaParceiro(parceiroForm) ;
        ParceiroDto parceiroDto = new ParceiroDto(parceiro);
		return ResponseEntity.ok().body(parceiroDto);
	}
    @PutMapping(value = "/{parceiroId}")
	public ResponseEntity<ParceiroDto> atualizaParceiro(@PathVariable Long parceiroId,@RequestBody ParceiroForm parceiroForm){
        Parceiro parceiro = parceiroService.atualizarParceiro(parceiroId,parceiroForm);

        ParceiroDto parceiroDto = new ParceiroDto(parceiro);
		return ResponseEntity.ok().body(parceiroDto);
	}
    @DeleteMapping("/{parceiroId}")
    public ResponseEntity<Void> deleteParceiro(@PathVariable Long parceiroId){
        parceiroService.apagaParceiro(parceiroId);
         return ResponseEntity.noContent().build();
    }
    
}
