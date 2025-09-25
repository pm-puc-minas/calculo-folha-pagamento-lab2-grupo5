package folha_de_pagamento.testes;

import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.Alimentacao;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;

public class AlimentacaoTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");
    BigDecimal valorAlimentacao = new BigDecimal(30);
    Funcionario funcionario = new Funcionario(salarioTotal, 20, valorAlimentacao);
    Alimentacao alimentacao = new Alimentacao();
    
    @Test
    public void testCalcularVale() {
        Assertions.assertEquals(new BigDecimal(600), alimentacao.calcularVale(funcionario));
    }
}
