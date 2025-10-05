package folha_de_pagamento.testes;

import folha_de_pagamento.model.FGTS;
import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.INSS;
import folha_de_pagamento.model.IRRF;
import folha_de_pagamento.model.Imposto;
import folha_de_pagamento.model.Trabalhador;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class TrabalhadorTest {

    Funcionario funcionario = new Funcionario("João", new java.math.BigDecimal("3000.00"));
    Trabalhador trabalhador = new Trabalhador(funcionario);

    Imposto inss = new INSS();
    Imposto irrf = new IRRF();
    Imposto fgts = new FGTS();

    @Test
    public void testVerDescontos() {
        assertEquals(new BigDecimal("263.33"), trabalhador.verDescontos(inss));
    }
}
