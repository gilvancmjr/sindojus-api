package br.org.sindojus.infrastructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import br.org.sindojus.domain.service.FotoStorageSevice;

@Service
public class LocalFotoStorageService implements  FotoStorageSevice{
    

    @Value("${sindojus.storage.local.diretorio-fotos}")
	private Path diretorioFotos;

    @Override
    public void armazenar(NovaFoto novaFoto){
        try{
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        }catch(Exception e){
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }
        
    }
    @Override
    public void remover(String nomeArquivo){
        try{
            Path arquivoPath = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(arquivoPath);
        }catch(Exception e){
            throw new StorageException("Não foi possível remover arquivo.", e);
        }

    }

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            return Files.newInputStream(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    } 
    private Path getArquivoPath(String nomeArquivo){
        return diretorioFotos.resolve(Path.of(nomeArquivo));

    }
}
