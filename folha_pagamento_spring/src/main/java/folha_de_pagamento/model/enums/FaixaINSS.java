package folha_de_pagamento.model.enums;
import java.math.BigDecimal;

public enum FaixaINSS {
    FAIXA_1(new BigDecimal("1302.00"), new BigDecimal("0.075"), BigDecimal.ZERO),
    FAIXA_2(new BigDecimal("2571.29"), new BigDecimal("0.09"), new BigDecimal("1302.00")),
    FAIXA_3(new BigDecimal("3856.94"), new BigDecimal("0.12"), new BigDecimal("2571.29")),
    FAIXA_4(new BigDecimal("7507.49"), new BigDecimal("0.14"), new BigDecimal("3856.94"));

    private final BigDecimal teto;
    private final BigDecimal aliquota;
    private final BigDecimal piso;

    FaixaINSS(BigDecimal teto, BigDecimal aliquota, BigDecimal piso) {
        this.teto = teto;
        this.aliquota = aliquota;
        this.piso = piso;
    }

    public BigDecimal getTeto() {
        return teto;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getPiso() {
        return piso;
    }
}