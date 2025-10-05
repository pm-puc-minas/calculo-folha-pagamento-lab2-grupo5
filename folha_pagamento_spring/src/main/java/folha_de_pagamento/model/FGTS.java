package folha_de_pagamento.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FGTS extends Imposto {
    public boolean dedutivel() {
        return true;
    }
    private BigDecimal aliquotaFGTS = new BigDecimal("0.08");
    
    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        return salarioBruto.multiply(aliquotaFGTS).setScale(2, RoundingMode.HALF_UP);
    }
}