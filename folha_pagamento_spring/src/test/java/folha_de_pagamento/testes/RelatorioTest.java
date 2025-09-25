package folha_de_pagamento.testes;

import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.Relatorio;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RelatorioTest {

    BigDecimal salarioTotal = new BigDecimal(3000);

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testCalcularSalarioHora() {
        Assertions.assertEquals(
                salarioTotal,
                new Relatorio().calcularSalarioHora(funcionario));
    }

    @Test
    public void testCalcularPericulosidade() {
            BigDecimal salario = new BigDecimal(3900).setScale(1);
            Assertions.assertEquals(
                    salario,
                    new Relatorio().calcularPericulosidade(funcionario, true));
    }

    @Test
    public void testGerarRelatorio() {
    }
}
