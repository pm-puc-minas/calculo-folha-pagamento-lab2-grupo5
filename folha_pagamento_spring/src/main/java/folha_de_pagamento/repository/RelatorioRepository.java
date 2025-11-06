package folha_de_pagamento.repository;

import folha_de_pagamento.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    List<Relatorio> findByFuncionarioId(Long funcionarioId);
    List<Relatorio> findByDateBetween(LocalDate inicio, LocalDate fim);
    List<Relatorio> findByFuncionarioIdAndDateBetween(Long funcionarioId, LocalDate inicio, LocalDate fim);
}
