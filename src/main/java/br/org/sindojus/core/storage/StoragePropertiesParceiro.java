package br.org.sindojus.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("sindojus.storage.parceiro")
public class StoragePropertiesParceiro {
    private Local local = new Local();
    private TipoStorage tipo = TipoStorage.LOCAL;

    enum TipoStorage {
        LOCAL, S3
    }


}
