package br.org.sindojus.domain.service;

import br.org.sindojus.domain.exception.FotoProdutoNaoEncontradaException;
import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.model.FotoParceiro;
import br.org.sindojus.domain.repository.FotoParceiroRepository;
import br.org.sindojus.domain.repository.NoticiaFotoRepository;
import br.org.sindojus.domain.service.FotoStorageSevice.NovaFoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoParceiroService {


    private FotoParceiroRepository fotoParceiroRepository;

    @Autowired
    private FotoStorageSevice fotoStorageSevice;

    @Autowired
	private FotoStorageSevice fotoStorage;

    @Transactional
    public FotoParceiro salvar(FotoParceiro parceiro, InputStream dadosArquivo){
        Long parceiroId = parceiro.getParceiro().getId();
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(parceiro.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoParceiro> fotoExistente = fotoParceiroRepository.findByParceiroId(parceiroId);
        if(fotoExistente.isPresent()){
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            fotoParceiroRepository.delete(fotoExistente.get());

        }
        parceiro.setNomeArquivo(nomeNovoArquivo);
        parceiro = fotoParceiroRepository.save(parceiro);
        fotoParceiroRepository.flush();
        
        NovaFoto novaFoto = NovaFoto.builder()
            .nomeArquivo(nomeNovoArquivo)
            .inputStream(dadosArquivo).build();

        fotoStorageSevice.substituir(nomeArquivoExistente, novaFoto, "noticia");
        return parceiro;
        
    }

    public FotoParceiro buscarOuFalhar(Long parceiroId) {
        return fotoParceiroRepository.findByParceiroId(parceiroId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(parceiroId));
    }  
    
}
