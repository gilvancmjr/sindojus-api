package br.org.sindojus.api.dto.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticacaoForm {
    private String login;
    private String senha;
}
