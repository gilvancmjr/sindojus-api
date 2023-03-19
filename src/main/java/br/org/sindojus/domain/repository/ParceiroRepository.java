package br.org.sindojus.domain.repository;

import br.org.sindojus.domain.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
}
