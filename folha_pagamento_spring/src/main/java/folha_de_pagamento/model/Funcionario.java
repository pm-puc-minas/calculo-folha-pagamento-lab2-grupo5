package folha_de_pagamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import folha_de_pagamento.model.enums.GrauInsalubridade;

public class Funcionario {
    private String nome;
    private LocalDate dataAdmissão;
    private String cargo;
    private BigDecimal salarioBruto;
    private int horasTrabalhadasPorDia;
    private int dependentes;
    private boolean possuiPericulosidade;
    private boolean possuiInsalubridade;
    private GrauInsalubridade grauInsalubridade;
    private BigDecimal valorValeTransporte;
    private BigDecimal valorValeAlimentacaoDiario;
    private int diasTrabalhadosNoMes;

    public Funcionario() {}
}
