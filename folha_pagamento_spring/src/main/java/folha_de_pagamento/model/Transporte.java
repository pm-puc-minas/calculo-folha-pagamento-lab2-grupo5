package folha_de_pagamento.model;

import java.math.BigDecimal;

public class Transporte extends Vale {

     public Transporte() {}
    
     @Override
     public BigDecimal calcularVale(Funcionario funcionario) { 
          
          BigDecimal valorTransporte  = funcionario.getValorTransporte();
          BigDecimal salarioBruto = funcionario.getSalarioBruto();
          BigDecimal valorCalculado = salarioBruto.multiply(new BigDecimal(0.06));

          if(valorCalculado.compareTo(valorTransporte) > 0) return valorTransporte;

          return valorCalculado;
     }
}
