package folha_de_pagamento.testes;

import folha_de_pagamento.model.Funcionario;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FuncionarioTest {

    BigDecimal salarioTotal = new BigDecimal("5000.00");

    Funcionario funcionario = new Funcionario(salarioTotal, 8, 20);

    @Test
    public void testGetSalarioBruto() {
        Assertions.assertEquals(salarioTotal, funcionario.getSalarioBruto());
    }

    @Test
    public void testGetHorasTrabalhadasPorDia() {
        Assertions.assertEquals(8, funcionario.getHorasTrabalhadasPorDia());
    }

    @Test
    public void testGetDiasTrabalhadosNoMes() {
        Assertions.assertEquals(20, funcionario.getDiasTrabalhadosNoMes());
    }
}
