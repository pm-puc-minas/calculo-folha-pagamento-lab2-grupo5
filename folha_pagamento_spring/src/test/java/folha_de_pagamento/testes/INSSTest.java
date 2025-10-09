package folha_de_pagamento.testes;

import folha_de_pagamento.model.imposto.INSS;
import folha_de_pagamento.model.user.Funcionario;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class INSSTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testCalcularINSS() {
        INSS inss = new INSS();
        Assertions.assertEquals(new BigDecimal("263.33"), inss.calcularImposto(funcionario));
    }
}
