package br.org.sindojus.api.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ParceiroForm {

    private String titulo;
    private String descricao;
    private MultipartFile arquivo;
    
    
}
