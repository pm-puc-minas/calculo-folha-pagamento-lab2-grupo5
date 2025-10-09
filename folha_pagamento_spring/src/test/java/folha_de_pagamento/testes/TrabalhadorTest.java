package folha_de_pagamento.testes;

import folha_de_pagamento.model.FGTS;
import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.Gestor;
import folha_de_pagamento.model.INSS;
import folha_de_pagamento.model.IRRF;
import folha_de_pagamento.model.Imposto;
import folha_de_pagamento.model.Trabalhador;
import folha_de_pagamento.model.Relatorio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TrabalhadorTest {

    Funcionario funcionario = new Funcionario("João", new java.math.BigDecimal("3000.00"));
    Trabalhador trabalhador = new Trabalhador(funcionario);
    Relatorio relatorio = new Relatorio();
    Gestor gestor = new Gestor();

    Imposto inss = new INSS();
    Imposto irrf = new IRRF();
    Imposto fgts = new FGTS();

    LocalDate dataContracheque = LocalDate.parse("2025-09-09");

    @Test
    public void testVerDescontos() {
        assertEquals(new BigDecimal("263.33"), trabalhador.verDescontos(inss));
    }

    @Test
    public void testVerContraCheque() {
        relatorio.setDate(dataContracheque);
        gestor.getRelatorios().add(relatorio);

        assertEquals(relatorio, trabalhador.verContraCheque(dataContracheque, gestor.getRelatorios()));
    }
}
