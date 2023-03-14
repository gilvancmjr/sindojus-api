package br.org.sindojus.domain.repository;

import br.org.sindojus.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
