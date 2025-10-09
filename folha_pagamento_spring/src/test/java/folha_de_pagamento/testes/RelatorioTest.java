package folha_de_pagamento.testes;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.user.Funcionario;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RelatorioTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testCalcularSalarioHora() {
        Assertions.assertEquals(
                new BigDecimal("18.75"),
                new Relatorio().calcularSalarioHora(funcionario));
    }

    @Test
    public void testCalcularPericulosidade() {

        boolean periculosidade = false;

        if (periculosidade) {
            Assertions.assertEquals(
                    new BigDecimal("3900.00"),
                    new Relatorio().calcularPericulosidade(funcionario, periculosidade));
        } else {
            Assertions.assertEquals(
                    salarioTotal,
                    new Relatorio().calcularPericulosidade(funcionario, periculosidade));
        }
    }
}
