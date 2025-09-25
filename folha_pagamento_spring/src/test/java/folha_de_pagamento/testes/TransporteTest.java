package folha_de_pagamento.testes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import folha_de_pagamento.model.Funcionario;
import folha_de_pagamento.model.Transporte;

public class TransporteTest {

    Funcionario funcionario = new Funcionario();
    Transporte transporte = new Transporte();

    BigDecimal valorTransporte = funcionario.getValorTransporte();

    @Test
    public void testeCalcularVale() { 
        funcionario.setValorTransporte(180);
        funcionario.setSalarioBruto(2000);
        
        Assertions.assertEquals(valorTransporte, transporte.calcularVale(funcionario));
     }
}
