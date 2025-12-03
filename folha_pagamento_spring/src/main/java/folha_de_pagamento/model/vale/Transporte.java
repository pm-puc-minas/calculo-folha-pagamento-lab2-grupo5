package folha_de_pagamento.model.vale;

import java.math.BigDecimal;
import folha_de_pagamento.model.user.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transporte extends Vale {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private double valorTransporte;

     public Transporte() {}

     public Transporte(double valorTransporte) {
          this.valorTransporte = valorTransporte;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public double getValorTransporte() {
          return valorTransporte;
     }

     public void setValorTransporte(double valorTransporte) {
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