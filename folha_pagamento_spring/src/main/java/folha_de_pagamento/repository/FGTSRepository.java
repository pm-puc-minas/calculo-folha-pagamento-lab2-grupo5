package folha_de_pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import folha_de_pagamento.model.imposto.FGTS;

@Repository
public interface FGTSRepository extends JpaRepository<FGTS, Long> {}
