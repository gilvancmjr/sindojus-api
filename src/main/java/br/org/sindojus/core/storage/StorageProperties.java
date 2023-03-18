package br.org.sindojus.core.storage;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("sindojus.storage")
public class StorageProperties {
    private Local local = new Local();
    private TipoStorage tipo = TipoStorage.LOCAL;
}

enum TipoStorage {
    LOCAL, S3
}

