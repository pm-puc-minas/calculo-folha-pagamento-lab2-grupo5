package folha_de_pagamento.model.user;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.imposto.Imposto;

public interface IFuncionario {

    public BigDecimal verDescontos(Imposto imposto);
    public Relatorio verContraCheque(LocalDate date, ArrayList<Relatorio> relatorio);
}
