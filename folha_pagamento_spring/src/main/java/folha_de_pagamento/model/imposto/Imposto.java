package folha_de_pagamento.model.imposto;

import java.math.BigDecimal;

import folha_de_pagamento.model.user.Funcionario;

public abstract class Imposto {
    public abstract BigDecimal calcularImposto(Funcionario funcionario);
}
