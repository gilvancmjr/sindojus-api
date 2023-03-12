package br.org.sindojus.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.sindojus.domain.exception.FotoProdutoNaoEncontradaException;
import br.org.sindojus.domain.model.FotoNoticia;
import br.org.sindojus.domain.repository.NoticiaFotoRepository;
import br.org.sindojus.domain.service.FotoStorageSevice.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

    
    @Autowired
    private NoticiaFotoRepository noticiaFotoRepository;

    @Autowired
    private FotoStorageSevice fotoStorageSevice;

    @Autowired
	private FotoStorageSevice fotoStorage;

    @Transactional
    public FotoNoticia salvar(FotoNoticia fotoNoticia, InputStream dadosArquivo){
        Long noticiaId = fotoNoticia.getNoticia().getId();
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(fotoNoticia.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoNoticia> fotoExistente = noticiaFotoRepository.findByNoticiaId(noticiaId);
        if(fotoExistente.isPresent()){
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            noticiaFotoRepository.delete(fotoExistente.get());

        }
        fotoNoticia.setNomeArquivo(nomeNovoArquivo);
        fotoNoticia = noticiaFotoRepository.save(fotoNoticia);
        noticiaFotoRepository.flush();
        
        NovaFoto novaFoto = NovaFoto.builder()
            .nomeArquivo(nomeNovoArquivo)
            .inputStream(dadosArquivo).build();

        fotoStorageSevice.substituir(nomeArquivoExistente, novaFoto);
        return fotoNoticia;
        
    }

    public FotoNoticia buscarOuFalhar(Long noticiaId) {
        return noticiaFotoRepository.findByNoticiaId(noticiaId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(noticiaId));
    }  
    
}
