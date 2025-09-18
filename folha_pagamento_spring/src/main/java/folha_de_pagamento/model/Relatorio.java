package folha_de_pagamento.model;

import java.math.BigDecimal;

public class Relatorio {
    
    private INSS valorINSS;
    private FGTS valorFGTS;
    private IRRF valorIRRF;

    private Transporte valorTransporte;
    private Alimentacao valorAlimentacao;

    public Relatorio() {}

    public BigDecimal calcularSalarioHora(Funcionario funcionario) { return null; }

    public BigDecimal calcularPericulosidade(Funcionario funcionario, boolean possuiPericulosidade) { return null; }

    public BigDecimal calcularInsalubridade(Funcionario funcionario, boolean possuiInsalubridade) { return null; }

    public void gerarRelatorio(Funcionario funcionario) {}
}
