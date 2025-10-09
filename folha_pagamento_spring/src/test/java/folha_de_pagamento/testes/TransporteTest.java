package folha_de_pagamento.testes;

import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.Transporte;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TransporteTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testCalcularValeMaiorQueSeisPorCento() {
        BigDecimal valorDescontado = salarioTotal.multiply(new BigDecimal("0.06"));
        Transporte transporte = new Transporte(600);
        Assertions.assertEquals(valorDescontado, transporte.calcularVale(funcionario));
    }

    @Test
    public void testCalcularValeMenorQueSeisPorCento() {
        Transporte transporte = new Transporte(100);
        Assertions.assertEquals(new BigDecimal("100"), transporte.calcularVale(funcionario));
    }
}
