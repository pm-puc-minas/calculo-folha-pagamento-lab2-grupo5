package folha_de_pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import folha_de_pagamento.model.imposto.INSS;

@Repository
public interface INSSRepository extends JpaRepository<INSS, Long> {}
