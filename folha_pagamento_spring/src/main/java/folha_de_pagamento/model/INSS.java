package folha_de_pagamento.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class INSS extends Imposto {

    // Limites das faixas de contribuição e aliquotas
    private static final BigDecimal FAIXA1_LIMITE = new BigDecimal("1302.00");
    private static final BigDecimal FAIXA2_LIMITE = new BigDecimal("2571.29");
    private static final BigDecimal FAIXA3_LIMITE = new BigDecimal("3856.94");
    private static final BigDecimal FAIXA4_LIMITE = new BigDecimal("7507.49"); 

    private static final BigDecimal FAIXA1_ALIQUOTA = new BigDecimal("0.075");
    private static final BigDecimal FAIXA2_ALIQUOTA = new BigDecimal("0.09");
    private static final BigDecimal FAIXA3_ALIQUOTA = new BigDecimal("0.12");
    private static final BigDecimal FAIXA4_ALIQUOTA = new BigDecimal("0.14");

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        BigDecimal contribuicaoTotal = BigDecimal.ZERO;

        // O cálculo é feito sobre o salário bruto, limitado ao teto.
        BigDecimal baseDeCalculo = salarioBruto.min(FAIXA4_LIMITE);

        // Soma a contribuição de cada faixa de forma progressiva
        contribuicaoTotal = contribuicaoTotal.add(calcularContribuicaoDaFaixa(baseDeCalculo, BigDecimal.ZERO, FAIXA1_LIMITE, FAIXA1_ALIQUOTA));
        contribuicaoTotal = contribuicaoTotal.add(calcularContribuicaoDaFaixa(baseDeCalculo, FAIXA1_LIMITE, FAIXA2_LIMITE, FAIXA2_ALIQUOTA));
        contribuicaoTotal = contribuicaoTotal.add(calcularContribuicaoDaFaixa(baseDeCalculo, FAIXA2_LIMITE, FAIXA3_LIMITE, FAIXA3_ALIQUOTA));
        contribuicaoTotal = contribuicaoTotal.add(calcularContribuicaoDaFaixa(baseDeCalculo, FAIXA3_LIMITE, FAIXA4_LIMITE, FAIXA4_ALIQUOTA));
        
        return contribuicaoTotal.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula a contribuição para uma faixa específica do INSS.
     *
     * @param baseDeCalculo O salário base para o cálculo.
     * @param limiteInferior O limite inferior da faixa.
     * @param limiteSuperior O limite superior da faixa.
     * @param aliquota A alíquota a ser aplicada.
     * @return O valor da contribuição para a faixa.
     */
    private BigDecimal calcularContribuicaoDaFaixa(BigDecimal baseDeCalculo, BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota) {
        // Se a base de cálculo não atingiu esta faixa, a contribuição é zero.
        if (baseDeCalculo.compareTo(limiteInferior) <= 0) {
            return BigDecimal.ZERO;
        }

        // Calcula o valor tributável DENTRO desta faixa específica.
        BigDecimal valorTributavelNaFaixa = baseDeCalculo.min(limiteSuperior).subtract(limiteInferior);
        
        return valorTributavelNaFaixa.multiply(aliquota);
    }
}