package folha_de_pagamento.model.imposto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.enums.FaixaINSS;

public class INSS extends Imposto {

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        BigDecimal contribuicaoTotal = BigDecimal.ZERO;

        for (FaixaINSS faixa : FaixaINSS.values()) {
            if (salarioBruto.compareTo(faixa.getPiso()) > 0) {
                BigDecimal baseDeCalculo = salarioBruto.min(faixa.getTeto());
                BigDecimal valorTributavelNaFaixa = baseDeCalculo.subtract(faixa.getPiso());
                contribuicaoTotal = contribuicaoTotal.add(valorTributavelNaFaixa.multiply(faixa.getAliquota()));
            }
        }

        return contribuicaoTotal.setScale(2, RoundingMode.HALF_UP);
    }
}