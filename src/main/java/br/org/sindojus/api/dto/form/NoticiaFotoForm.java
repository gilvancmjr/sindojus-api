package br.org.sindojus.api.dto.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticiaFotoForm {
    
    private MultipartFile arquivo;
}
