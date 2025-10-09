package folha_de_pagamento.model.vale;

import java.math.BigDecimal;

import folha_de_pagamento.model.user.Funcionario;

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
