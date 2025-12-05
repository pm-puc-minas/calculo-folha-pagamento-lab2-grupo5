package folha_de_pagamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import folha_de_pagamento.model.user.UsuarioDoSistema;

public interface UsuarioRepository extends JpaRepository<UsuarioDoSistema, Long> {
    Optional<UsuarioDoSistema> findByLogin(String login);
}
