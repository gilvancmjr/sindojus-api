package br.org.sindojus.infrastructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import br.org.sindojus.core.storage.StorageProperties;
import br.org.sindojus.core.storage.StoragePropertiesParceiro;
import br.org.sindojus.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import br.org.sindojus.domain.service.FotoStorageSevice;

@Service
public class LocalFotoStorageService implements  FotoStorageSevice{


//    @Value("${sindojus.storage.local.diretorio-fotos}")
//	private Path diretorioFotos;

    @Autowired
    private StorageProperties storageProperties;
    @Autowired
    private StoragePropertiesParceiro propertiesParceiro;

    @Override
    public void armazenar(NovaFoto novaFoto, String tipoEntidade){
        try{
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo(),tipoEntidade);
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        }catch(Exception e){
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }

    }
    @Override
    public void remover(String nomeArquivo, String tipoEntidade){
        try{
            Path arquivoPath = getArquivoPath(nomeArquivo,tipoEntidade);
            Files.deleteIfExists(arquivoPath);
        }catch(Exception e){
            throw new StorageException("Não foi possível remover arquivo.", e);
        }

    }

    @Override
    public InputStream recuperar(String nomeArquivo, String tipoEntidade) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo,tipoEntidade);

            return Files.newInputStream(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo, String tipoEntidade) {
        if (tipoEntidade.equals("noticia")){
            return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
        }
        if (tipoEntidade.equals("parceiro")) {
            return propertiesParceiro.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
        }
        throw new NegocioException("Erro interno no caminho do servidor de armazenamento");

    }
}