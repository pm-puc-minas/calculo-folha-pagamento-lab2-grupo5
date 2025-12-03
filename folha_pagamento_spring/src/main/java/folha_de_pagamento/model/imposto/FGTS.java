package folha_de_pagamento.model.imposto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import folha_de_pagamento.model.user.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FGTS extends Imposto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final BigDecimal aliquotaFGTS = new BigDecimal("0.08");
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean dedutivel() {
        return true;
    }
    
    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        return salarioBruto.multiply(aliquotaFGTS).setScale(2, RoundingMode.HALF_UP);
    }
}