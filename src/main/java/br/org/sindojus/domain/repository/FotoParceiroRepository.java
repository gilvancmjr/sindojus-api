package br.org.sindojus.domain.repository;

import br.org.sindojus.domain.model.FotoParceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoParceiroRepository extends JpaRepository<FotoParceiro, Long> {

    Optional<FotoParceiro> findByParceiroId(Long parceiroId);

}
