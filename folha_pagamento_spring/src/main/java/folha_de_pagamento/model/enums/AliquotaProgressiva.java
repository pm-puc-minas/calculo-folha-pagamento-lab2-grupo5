package folha_de_pagamento.model.enums;

import java.math.BigDecimal;

public enum AliquotaProgressiva {
    PRIMEIRA("Primeira Faixa","7.5"),
    SEGUNDA("Primeira Faixa","9"),
    TERCEIRA("Primeira Faixa","12"), 
    QUARTA("Primeira Faixa","14");    

    private String faixa; 
    private final BigDecimal aliquota; 
}

AliquotaProgressiva(String faixa, BigDecimal aliquota){
    this.faixa = faixa; 
    this.aliquota = aliquota;
}

