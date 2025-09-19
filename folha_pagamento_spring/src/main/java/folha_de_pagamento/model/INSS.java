package folha_de_pagamento.model;

import java.math.BigDecimal;

public class INSS extends Imposto {
    public boolean dedutivel() {
        return true;
    }

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        return null;
    }
}
