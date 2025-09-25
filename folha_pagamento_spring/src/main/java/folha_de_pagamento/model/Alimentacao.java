package folha_de_pagamento.model;

import java.math.BigDecimal;

public class Alimentacao extends Vale {

    public Alimentacao() {}
    
    @Override
    public BigDecimal calcularVale(Funcionario funcionario) { 
        
        BigDecimal valorAlimentacaoDiario = funcionario.getValorAlimentacao();
        int diasTrabalhadosNoMes = funcionario.getDiasTrabalhadosNoMes();
        BigDecimal valorVale = valorAlimentacaoDiario.multiply(new BigDecimal(diasTrabalhadosNoMes));
        
        return valorVale;
     }
}
