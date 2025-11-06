package folha_de_pagamento.testes;

import folha_de_pagamento.DemoApplication;
import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.repository.FuncionarioRepository;
import folha_de_pagamento.repository.RelatorioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = DemoApplication.class)
class RelatorioPersistenciaTest {

    @Autowired
    private RelatorioRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    void testSalvarRelatorio() {
        Funcionario func = funcionarioRepository.save(
            new Funcionario("Joao", new BigDecimal("5000.00"))
        );
        
        Relatorio relatorio = new Relatorio(LocalDate.now());
        relatorio.setFuncionario(func);
        relatorio.setSalarioBruto(new BigDecimal("5000.00"));
        relatorio.setSalarioLiquido(new BigDecimal("4000.00"));
        
        Relatorio salvo = repository.save(relatorio);
        
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getSalarioBruto()).isEqualByComparingTo("5000.00");
    }

    @Test
    void testBuscarRelatorio() {
        Funcionario func = funcionarioRepository.save(
            new Funcionario("Maria", new BigDecimal("4000.00"))
        );
        
        Relatorio relatorio = new Relatorio(LocalDate.now());
        relatorio.setFuncionario(func);
        relatorio.setSalarioBruto(new BigDecimal("4000.00"));
        relatorio.setSalarioLiquido(new BigDecimal("3500.00"));
        Relatorio salvo = repository.save(relatorio);
        
        var resultado = repository.findById(salvo.getId());
        
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getSalarioLiquido()).isEqualByComparingTo("3500.00");
    }

    @Test
    void testBuscarRelatoriosPorFuncionario() {
        Funcionario func = funcionarioRepository.save(
            new Funcionario("Pedro", new BigDecimal("6000.00"))
        );
        
        Relatorio rel1 = new Relatorio(LocalDate.of(2025, 10, 1));
        rel1.setFuncionario(func);
        rel1.setSalarioBruto(new BigDecimal("6000.00"));
        rel1.setSalarioLiquido(new BigDecimal("5000.00"));
        repository.save(rel1);
        
        Relatorio rel2 = new Relatorio(LocalDate.of(2025, 11, 1));
        rel2.setFuncionario(func);
        rel2.setSalarioBruto(new BigDecimal("6000.00"));
        rel2.setSalarioLiquido(new BigDecimal("5000.00"));
        repository.save(rel2);
        
        var relatorios = repository.findByFuncionarioId(func.getId());
        
        assertThat(relatorios).hasSize(2);
    }

    @Test
    void testDeletarRelatorio() {
        Funcionario func = funcionarioRepository.save(
            new Funcionario("Temp", new BigDecimal("3000.00"))
        );
        
        Relatorio relatorio = new Relatorio(LocalDate.now());
        relatorio.setFuncionario(func);
        relatorio.setSalarioBruto(new BigDecimal("3000.00"));
        relatorio.setSalarioLiquido(new BigDecimal("2500.00"));
        Relatorio salvo = repository.save(relatorio);
        
        repository.deleteById(salvo.getId());
        
        assertThat(repository.findById(salvo.getId())).isEmpty();
    }
}
