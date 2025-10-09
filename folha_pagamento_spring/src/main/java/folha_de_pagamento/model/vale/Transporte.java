package folha_de_pagamento.model.vale;

import java.math.BigDecimal;

import folha_de_pagamento.model.user.Funcionario;

public class Transporte extends Vale {

     private double valorTransporte;

     public Transporte() {}

     public Transporte(double valorTransporte) {
          this.valorTransporte = valorTransporte;
     }
    
     @Override
     public BigDecimal calcularVale(Funcionario funcionario) { 
          BigDecimal salarioBruto = funcionario.getSalarioBruto();
          BigDecimal valorCalculado = salarioBruto.multiply(new BigDecimal("0.06"));
          BigDecimal valorTransporte = new BigDecimal(this.valorTransporte);

          if(valorCalculado.compareTo(valorTransporte) > 0) return valorTransporte;

          return valorCalculado;
     }
}
