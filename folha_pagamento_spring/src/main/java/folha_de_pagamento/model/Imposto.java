package folha_de_pagamento.model;

import java.math.BigDecimal;

public abstract class Imposto {
    public abstract BigDecimal calcularImposto(Funcionario funcionario);
}
