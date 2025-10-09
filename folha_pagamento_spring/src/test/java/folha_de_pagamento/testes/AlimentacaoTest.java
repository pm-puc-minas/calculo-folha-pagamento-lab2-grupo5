package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.vale.Alimentacao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class AlimentacaoTest {

    @Test
    public void testCalcularVale() {
        BigDecimal salario = new BigDecimal("3000.00");
        int dias = 20;
        double valorDia = 30.0;

        Funcionario funcionario = new Funcionario(salario, 8, dias);
        Alimentacao beneficio = new Alimentacao(valorDia);

        BigDecimal esperado = BigDecimal.valueOf(valorDia).multiply(BigDecimal.valueOf(dias));
        BigDecimal calculado = beneficio.calcularVale(funcionario);

        Assertions.assertEquals(0, esperado.compareTo(calculado));
    }
}
