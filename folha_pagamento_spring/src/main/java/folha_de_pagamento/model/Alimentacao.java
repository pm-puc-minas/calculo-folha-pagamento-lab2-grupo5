package folha_de_pagamento.model;

import java.math.BigDecimal;

public class Alimentacao extends Vale {

    private double valorAlimentacao;

    public Alimentacao() {}

    public Alimentacao(double valorAlimentacao) {
        this.valorAlimentacao = valorAlimentacao;
    }
    
    @Override
    public BigDecimal calcularVale(Funcionario funcionario) { 
        
        int diasTrabalhadosNoMes = funcionario.getDiasTrabalhadosNoMes();
        double valorVale = valorAlimentacao * diasTrabalhadosNoMes;
        
        return new BigDecimal(valorVale);
     }
}
