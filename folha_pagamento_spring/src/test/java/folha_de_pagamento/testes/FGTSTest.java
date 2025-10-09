package folha_de_pagamento.testes;

import folha_de_pagamento.model.FGTS;
import folha_de_pagamento.model.user.Funcionario;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FGTSTest {

    BigDecimal salarioTotal = new BigDecimal("3000.00");

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testCalcularFGTS() {
        FGTS fgts = new FGTS();
        Assertions.assertEquals(new BigDecimal("240.00"), fgts.calcularImposto(funcionario));
    }
}
