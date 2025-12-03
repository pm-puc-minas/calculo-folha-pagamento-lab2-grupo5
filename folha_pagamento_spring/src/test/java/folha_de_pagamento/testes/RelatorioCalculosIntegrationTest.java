package folha_de_pagamento.testes;

import folha_de_pagamento.DemoApplication;
import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.enums.GrauInsalubridade;
import folha_de_pagamento.model.user.Funcionario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DemoApplication.class)
class RelatorioCalculosIntegrationTest {

    private Funcionario buildFuncionario(BigDecimal salarioBruto, int horasDia, int diasMes, GrauInsalubridade grau) {
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBruto(salarioBruto);
        funcionario.setHorasTrabalhadasPorDia(horasDia);
        funcionario.setDiasTrabalhadosNoMes(diasMes);
        funcionario.setGrauInsalubridade(grau);
        return funcionario;
    }

    @Test
    @DisplayName("Calcular salário hora (Funcionario 100: 3000 / (8*22) = 17.05)")
    void calcularSalarioHora() {
        Funcionario f = buildFuncionario(new BigDecimal("3000.00"), 8, 22, GrauInsalubridade.BAIXO);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal salarioHora = r.calcularSalarioHora(f);
        assertThat(salarioHora).isEqualByComparingTo("17.05");
    }

    @Test
    @DisplayName("Calcular periculosidade (Funcionario 101: 5200 + 30% = 6760)")
    void calcularPericulosidade() {
        Funcionario f = buildFuncionario(new BigDecimal("5200.00"), 8, 22, GrauInsalubridade.BAIXO);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularPericulosidade(f, true);
        assertThat(total).isEqualByComparingTo("6760.00");
    }

    @Test
    @DisplayName("Calcular insalubridade ALTO (Funcionario 102: salario + 40% se grau=ALTO)")
    void calcularInsalubridadeAlto() {
        Funcionario f = buildFuncionario(new BigDecimal("4000.00"), 8, 22, GrauInsalubridade.ALTO);
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
        Funcionario f = buildFuncionario(new BigDecimal("3000.00"), 8, 22, GrauInsalubridade.BAIXO);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularPericulosidade(f, false);
        assertThat(total).isEqualByComparingTo(f.getSalarioBruto());
    }

    @Test
    @DisplayName("Sem insalubridade mantém salário bruto")
    void semInsalubridade() {
        Funcionario f = buildFuncionario(new BigDecimal("3000.00"), 8, 22, GrauInsalubridade.BAIXO);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularInsalubridade(f, false);
        assertThat(total).isEqualByComparingTo(f.getSalarioBruto());
    }
}
