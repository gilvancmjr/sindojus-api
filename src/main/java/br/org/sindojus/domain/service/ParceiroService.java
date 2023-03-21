package br.org.sindojus.domain.service;


import br.org.sindojus.api.dto.form.ParceiroForm;
import br.org.sindojus.domain.exception.NoticiaNaoEncontrada;
import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.Parceiro;
import br.org.sindojus.domain.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ParceiroService {
    @Autowired
    private ParceiroRepository parceiroRepository;

    @Autowired
    private FotoStorageSevice fotoStorageSevice;

    public List<Parceiro> list() {
        return parceiroRepository.findAll();
    }

    public Parceiro buscarParceiro(Long parceiroId) {
        return buscarOuFalhar(parceiroId);
    }

    @Transactional
    public Parceiro criaParceiro(ParceiroForm parceiroForm, InputStream dadosArquivo) {
        String nomeNovoArquivo = fotoStorageSevice.gerarNomeArquivo(parceiroForm.getArquivo().getOriginalFilename());
        Parceiro parceiro = new Parceiro();
        parceiro.setTitulo(parceiroForm.getTitulo());
        parceiro.setDescricao(parceiroForm.getDescricao());
        parceiro.setNomeArquivo(nomeNovoArquivo);
        parceiro.setContentType(parceiroForm.getArquivo().getContentType());
        parceiro.setTamanho(parceiroForm.getArquivo().getSize());
        parceiro = parceiroRepository.save(parceiro);
        parceiroRepository.flush();
        salvarImagem(null,nomeNovoArquivo,dadosArquivo );
        return parceiro;
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

    private void salvarImagem(String nomeArquivoExistente,String nomeNovoArquivo, InputStream dadosArquivo) {



//        Parceiro parceiro = new Parceiro();
//        parceiro.setTitulo(parceiroForm.getTitulo());
//        parceiro.setDescricao(parceiroForm.getDescricao());
//        parceiro.setNomeArquivo(nomeNovoArquivo);
//        parceiro.setContentType(parceiroForm.getArquivo().getContentType());
//        parceiro.setTamanho(parceiroForm.getArquivo().getSize());
//
//        parceiro = parceiroRepository.save(parceiro);
//        parceiroRepository.flush();

        FotoStorageSevice.NovaFoto novaFoto = FotoStorageSevice.NovaFoto.builder()
                .nomeArquivo(nomeNovoArquivo)
                .inputStream(dadosArquivo).build();

        fotoStorageSevice.substituir(nomeArquivoExistente, novaFoto, "noticia");

    }

}
