package br.org.sindojus.api.controller;

import br.org.sindojus.api.dto.TokenUsuarioDto;
import br.org.sindojus.api.dto.form.AutenticacaoForm;
import br.org.sindojus.core.TokenService;
import br.org.sindojus.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenUsuarioDto> efetuarLogin(@RequestBody @Valid AutenticacaoForm autenticacaoForm) {
        var authenticationToken  = new UsernamePasswordAuthenticationToken(autenticacaoForm.getLogin(), autenticacaoForm.getSenha());
        var authentication = manager.authenticate(authenticationToken );
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        TokenUsuarioDto tokenUsuarioDto = new TokenUsuarioDto();
        tokenUsuarioDto.setToken(tokenJWT);
        return ResponseEntity.ok(tokenUsuarioDto);



    }
}
