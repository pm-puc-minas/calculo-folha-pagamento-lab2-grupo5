package folha_de_pagamento.testes;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.user.Funcionario;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class InsalubridadeTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");

    @Test
    public void testCalcularInsalubridadeBaixo() {
        Funcionario funcionario = new Funcionario("João", salarioTotal);
        Relatorio relatorio = new Relatorio();
        Assertions.assertEquals(salarioTotal, relatorio.calcularInsalubridade(funcionario, false));
    }

    @Test
    public void testCalcularInsalubridadeMedio() {
        Funcionario funcionario = new Funcionario("Marcelo", salarioTotal);
        Relatorio relatorio = new Relatorio();
        Assertions.assertEquals(salarioTotal, relatorio.calcularInsalubridade(funcionario, false));
    }

    @Test
    public void testCalcularInsalubridadeAlto() {
        Funcionario funcionario = new Funcionario("Bernardo", salarioTotal);
        Relatorio relatorio = new Relatorio();
        Assertions.assertEquals(salarioTotal, relatorio.calcularInsalubridade(funcionario, false));
    }

    @Test
    public void testCalcularInsalubridadeSemInsalubridade() {
        Funcionario funcionario = new Funcionario("Lucas", salarioTotal);
        Relatorio relatorio = new Relatorio();
        Assertions.assertEquals(salarioTotal, relatorio.calcularInsalubridade(funcionario, false));
    }
}
