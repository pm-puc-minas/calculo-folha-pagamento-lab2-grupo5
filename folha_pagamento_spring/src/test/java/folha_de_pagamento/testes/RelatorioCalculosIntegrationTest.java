package folha_de_pagamento.testes;

import folha_de_pagamento.DemoApplication;
import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.repository.FuncionarioRepository;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DemoApplication.class)
class RelatorioCalculosIntegrationTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private Funcionario getFuncionarioOrSkip(Long idEsperado) {
        Optional<Funcionario> opt = funcionarioRepository.findById(idEsperado);
        Assumptions.assumeTrue(opt.isPresent(), "Funcionario id=" + idEsperado + " precisa existir na base (execute os INSERTs antes).");
        return opt.get();
    }

    @Test
    @DisplayName("Calcular salário hora (Funcionario 100: 3000 / (8*22) = 17.05)")
    void calcularSalarioHora() {
        Funcionario f = getFuncionarioOrSkip(100L);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal salarioHora = r.calcularSalarioHora(f);
        assertThat(salarioHora).isEqualByComparingTo("17.05");
    }

    @Test
    @DisplayName("Calcular periculosidade (Funcionario 101: 5200 + 30% = 6760)")
    void calcularPericulosidade() {
        Funcionario f = getFuncionarioOrSkip(101L);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularPericulosidade(f, true);
        assertThat(total).isEqualByComparingTo("6760.00");
    }

    @Test
    @DisplayName("Calcular insalubridade ALTO (Funcionario 102: salario + 40% se grau=ALTO)")
    void calcularInsalubridadeAlto() {
        Funcionario f = getFuncionarioOrSkip(102L);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularInsalubridade(f, true);
        if (f.getGrauInsalubridade() == folha_de_pagamento.model.enums.GrauInsalubridade.ALTO) {
            BigDecimal esperado = f.getSalarioBruto().multiply(BigDecimal.valueOf(1.40)).setScale(2);
            assertThat(total).isEqualByComparingTo(esperado);
        } else {
            BigDecimal fator;
            switch (f.getGrauInsalubridade()) {
                case MEDIO -> fator = BigDecimal.valueOf(1.20);
                case BAIXO -> fator = BigDecimal.valueOf(1.10);
                default -> fator = BigDecimal.ONE;
            }
            BigDecimal esperado = f.getSalarioBruto().multiply(fator).setScale(2);
            assertThat(total).isEqualByComparingTo(esperado);
        }
    }

    @Test
    @DisplayName("Sem periculosidade mantém salário bruto")
    void semPericulosidade() {
        Funcionario f = getFuncionarioOrSkip(100L);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularPericulosidade(f, false);
        assertThat(total).isEqualByComparingTo(f.getSalarioBruto());
    }

    @Test
    @DisplayName("Sem insalubridade mantém salário bruto")
    void semInsalubridade() {
        Funcionario f = getFuncionarioOrSkip(100L);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularInsalubridade(f, false);
        assertThat(total).isEqualByComparingTo(f.getSalarioBruto());
    }
}
