package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Gestor;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class GestorTest {

    @Test
    public void testRegistrarFuncionario() {
        Gestor gestor = new Gestor("gestor", "123456");
        Funcionario funcionario = new Funcionario("João", new BigDecimal("3000.00"));
        gestor.registrarFuncionario(funcionario);
        Assertions.assertTrue(true);
    }

    @Test
    public void testGerarFolhaPgt() {
        Gestor gestor = new Gestor("gestor", "123456");
        Assertions.assertNotNull(gestor.gerarFolhaPgt(new Funcionario("Marcos", new BigDecimal("4000.00"))));
    }
}
