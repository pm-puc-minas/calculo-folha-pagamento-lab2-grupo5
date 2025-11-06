package folha_de_pagamento.repository;

import folha_de_pagamento.model.user.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {
    Optional<Gestor> findByLogin(String login);
    boolean existsByLogin(String login);
}
