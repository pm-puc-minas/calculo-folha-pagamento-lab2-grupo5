package folha_de_pagamento.model;

import java.math.BigDecimal;

//  RF6 - Calcular Desconto de INSS: O sistema deve ser capaz de calcular o desconto de INSS;
//  RF7 - Calcular FGTS: O sistema deve ser capaz de calcular o desconto de FGTS;
//  RF8 - Calcular Desconto de IRRF: O sistema deve ser capaz de calcular o desconto de IRRF;


public abstract class Imposto {
    public abstract BigDecimal calcularImposto(Funcionario funcionario);
    
}   

