package br.org.sindojus.api.dto.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticiaForm {

    private String titulo;
    private String descricao;
    private MultipartFile arquivo;
    
    
}
