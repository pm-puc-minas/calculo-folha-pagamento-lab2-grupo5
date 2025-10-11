package folha_de_pagamento.model.imposto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import folha_de_pagamento.model.user.Funcionario;

public class IRRF extends Imposto {

    private static final BigDecimal DEDUCAO_POR_DEPENDENTE = new BigDecimal("189.59");

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        INSS inss = new INSS();
        BigDecimal descontoINSS = inss.calcularImposto(funcionario);

        BigDecimal deducaoDependentes = DEDUCAO_POR_DEPENDENTE.multiply(new BigDecimal(funcionario.getNumeroDependentes()));

        BigDecimal baseDeCalculo = funcionario.getSalarioBruto().subtract(descontoINSS).subtract(deducaoDependentes);

        for (TabelaIRRF faixa : TabelaIRRF.values()) {
            if (baseDeCalculo.compareTo(faixa.getBaseMinima()) >= 0) {
                BigDecimal impostoCalculado = baseDeCalculo.multiply(faixa.getAliquota()).subtract(faixa.getParcelaADeduzir());
                return impostoCalculado.max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            }
        }

        return BigDecimal.ZERO;
    }
}