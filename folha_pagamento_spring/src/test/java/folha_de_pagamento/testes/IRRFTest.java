package folha_de_pagamento.testes;

import folha_de_pagamento.model.IRRF;
import folha_de_pagamento.model.Funcionario;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class IRRFTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testCalcularIRRF() {
        IRRF irrf = new IRRF();
        Assertions.assertEquals(new BigDecimal("62.45"), irrf.calcularImposto(funcionario));
    }
}
