package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.model.user.IFuncionario;
import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.imposto.FGTS;
import folha_de_pagamento.model.imposto.INSS;
import folha_de_pagamento.model.imposto.IRRF;
import folha_de_pagamento.model.imposto.Imposto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TrabalhadorTest {

    Funcionario funcionario = new Funcionario("João", new java.math.BigDecimal("3000.00"));
    IFuncionario funcionario2 = (IFuncionario) (Object) new Funcionario("João", new java.math.BigDecimal("3000.00"));    
    Relatorio relatorio = new Relatorio();
    Gestor gestor = new Gestor();

    Imposto inss = new INSS();
    Imposto irrf = new IRRF();
    Imposto fgts = new FGTS();

    LocalDate dataContracheque = LocalDate.parse("2025-09-09");

    @Test
    public void testVerDescontos() {
        assertEquals(new BigDecimal("263.33"), funcionario2.verDescontos(inss));
    }

    @Test
    public void testVerContraCheque() {
        relatorio.setDate(dataContracheque);
        gestor.getRelatorios().add(relatorio);

        assertEquals(relatorio, funcionario2.verContraCheque(dataContracheque, gestor.getRelatorios()));
    }
}
