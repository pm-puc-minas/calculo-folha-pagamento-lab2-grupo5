package folha_de_pagamento.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class IRRF extends Imposto {
    
    private static final BigDecimal DEDUCAO_POR_DEPENDENTE = new BigDecimal("189.59");

    // Usamos um 'record' para representar cada faixa de forma imutável e concisa.
    private record FaixaIRRF(BigDecimal baseMinima, BigDecimal aliquota, BigDecimal parcela) {}

    // A tabela de incidência agora é uma lista de objetos, muito mais fácil de manter.
    private static final List<FaixaIRRF> TABELA_IRRF = List.of(
        new FaixaIRRF(new BigDecimal("4664.69"), new BigDecimal("0.275"), new BigDecimal("869.36")),
        new FaixaIRRF(new BigDecimal("3751.06"), new BigDecimal("0.225"), new BigDecimal("636.13")),
        new FaixaIRRF(new BigDecimal("2826.66"), new BigDecimal("0.15"), new BigDecimal("354.80")),
        new FaixaIRRF(new BigDecimal("1903.99"), new BigDecimal("0.075"), new BigDecimal("142.80"))
    );

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        INSS inss = new INSS();
        BigDecimal descontoINSS = inss.calcularImposto(funcionario);

        BigDecimal deducaoDependentes = DEDUCAO_POR_DEPENDENTE.multiply(new BigDecimal(funcionario.getNumeroDependentes()));

        BigDecimal baseDeCalculo = funcionario.getSalarioBruto().subtract(descontoINSS).subtract(deducaoDependentes);

        for (FaixaIRRF faixa : TABELA_IRRF) {
            if (baseDeCalculo.compareTo(faixa.baseMinima()) >= 0) {
                BigDecimal impostoCalculado = baseDeCalculo.multiply(faixa.aliquota()).subtract(faixa.parcela());
                return impostoCalculado.max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            }
        }
        
        return BigDecimal.ZERO;
    }
}