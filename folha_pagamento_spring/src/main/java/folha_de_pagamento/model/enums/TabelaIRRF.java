package folha_de_pagamento.model.enums;

import java.math.BigDecimal;

public enum TabelaIRRF {
    FAIXA_5(new BigDecimal("4664.69"), new BigDecimal("0.275"), new BigDecimal("869.36")),
    FAIXA_4(new BigDecimal("3751.06"), new BigDecimal("0.225"), new BigDecimal("636.13")),
    FAIXA_3(new BigDecimal("2826.66"), new BigDecimal("0.15"), new BigDecimal("354.80")),
    FAIXA_2(new BigDecimal("1903.99"), new BigDecimal("0.075"), new BigDecimal("142.80")),
    FAIXA_1(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

    private final BigDecimal baseMinima;
    private final BigDecimal aliquota;
    private final BigDecimal parcelaADeduzir;

    TabelaIRRF(BigDecimal baseMinima, BigDecimal aliquota, BigDecimal parcelaADeduzir) {
        this.baseMinima = baseMinima;
        this.aliquota = aliquota;
        this.parcelaADeduzir = parcelaADeduzir;
    }

    public BigDecimal getBaseMinima() {
        return baseMinima;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getParcelaADeduzir() {
        return parcelaADeduzir;
    }
}