package br.org.sindojus.domain.service;


import br.org.sindojus.api.dto.form.ParceiroForm;
import br.org.sindojus.domain.exception.NoticiaNaoEncontrada;
import br.org.sindojus.domain.model.Parceiro;
import br.org.sindojus.domain.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParceiroService {
    @Autowired
    private ParceiroRepository parceiroRepository;

    public List<Parceiro> list() {
        return parceiroRepository.findAll();
    }
    public Parceiro buscarParceiro(Long parceiroId) {
        return buscarOuFalhar(parceiroId);
    }
    public Parceiro criaParceiro(ParceiroForm parceiroForm){
        Parceiro parceiro = new Parceiro();
        parceiro.setTitulo(parceiroForm.getTitulo());
        parceiro.setDescricao(parceiroForm.getDescricao());
        return parceiroRepository.save(parceiro);
    }
    public Parceiro atualizarParceiro(Long parceiroId, ParceiroForm parceiroForm) {
        Parceiro parceiro = buscarOuFalhar(parceiroId);
        parceiro.setTitulo(parceiroForm.getTitulo());
        parceiro.setDescricao(parceiroForm.getDescricao());
        return parceiroRepository.save(parceiro);

    }
    public void apagaParceiro(Long noticiaId) {
        buscarOuFalhar(noticiaId);
        parceiroRepository.deleteById(noticiaId);
    }
    private Parceiro buscarOuFalhar(Long parceiroId) {
        return parceiroRepository.findById(parceiroId)
                .orElseThrow(() -> new NoticiaNaoEncontrada(parceiroId));
    }
}
