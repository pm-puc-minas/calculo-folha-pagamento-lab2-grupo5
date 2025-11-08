package folha_de_pagamento.repository;

import folha_de_pagamento.model.user.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByNome(String nome);
    List<Funcionario> findByGestorId(Long gestorId);
    List<Funcionario> findByCargo(String cargo);
    List<Funcionario> getFuncionariosComSalarioAcimaDe(BigDecimal valorMinimo);
}
