package folha_de_pagamento.testes;

import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.Alimentacao;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;

public class AlimentacaoTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");
    BigDecimal valorDescontado = salarioTotal.multiply(new BigDecimal(0.06));

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);
    Alimentacao alimentacao = new Alimentacao(600);
    
    @Test
    public void testCalcularVale() {
        Assertions.assertEquals(valorDescontado, alimentacao.calcularVale(funcionario));
    }
}
