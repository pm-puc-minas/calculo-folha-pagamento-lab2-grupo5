package folha_de_pagamento.model.vale;

import java.math.BigDecimal;
import folha_de_pagamento.model.user.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alimentacao extends Vale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valorAlimentacao;

    public Alimentacao() {}

    public Alimentacao(double valorAlimentacao) {
        this.valorAlimentacao = valorAlimentacao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorAlimentacao() {
        return valorAlimentacao;
    }

    public void setValorAlimentacao(double valorAlimentacao) {
        this.valorAlimentacao = valorAlimentacao;
    }
    
    @Override
    public BigDecimal calcularVale(Funcionario funcionario) { 
        int diasTrabalhadosNoMes = funcionario.getDiasTrabalhadosNoMes();
        double valorVale = valorAlimentacao * diasTrabalhadosNoMes;
        return new BigDecimal(valorVale);
     }
}