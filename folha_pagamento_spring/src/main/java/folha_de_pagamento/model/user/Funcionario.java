package folha_de_pagamento.model.user;

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

    public Funcionario() {
    }

    public Funcionario(String nome, BigDecimal salarioBruto) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
    }

    // Constructor utilizado para testes
    public Funcionario(BigDecimal salarioBruto, int horasTrabalhadasPorDia, int diasTrabalhadosNoMes) {
        this.salarioBruto = salarioBruto;
        this.horasTrabalhadasPorDia = horasTrabalhadasPorDia;
        this.diasTrabalhadosNoMes = diasTrabalhadosNoMes;
    }

    public Funcionario(String nome, LocalDate dataAdmissão, String cargo, BigDecimal salarioBruto,
            int horasTrabalhadasPorDia, int dependentes, boolean possuiPericulosidade, boolean possuiInsalubridade,
            GrauInsalubridade grauInsalubridade, BigDecimal valorValeTransporte,
            BigDecimal valorValeAlimentacaoDiario, int diasTrabalhadosNoMes) {
        this.nome = nome;
        this.dataAdmissão = dataAdmissão;
        this.cargo = cargo;
        this.salarioBruto = salarioBruto;
        this.horasTrabalhadasPorDia = horasTrabalhadasPorDia;
        this.dependentes = dependentes;
        this.possuiPericulosidade = possuiPericulosidade;
        this.possuiInsalubridade = possuiInsalubridade;
        this.grauInsalubridade = grauInsalubridade;
        this.valorValeTransporte = valorValeTransporte;
        this.valorValeAlimentacaoDiario = valorValeAlimentacaoDiario;
        this.diasTrabalhadosNoMes = diasTrabalhadosNoMes;
    }

    // Getters
    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public int getHorasTrabalhadasPorDia() {

        return horasTrabalhadasPorDia;
    }

    public int getDependentes() {
        return dependentes;
    }

    public boolean getPossuiPericulosidade() {
        return possuiPericulosidade;
    }

    public int getNumeroDependentes() {
        return dependentes;
    }

    public boolean getPossuiInsalubridade() {
        return possuiInsalubridade;
    }

    public GrauInsalubridade getGrauInsalubridade() {
        return grauInsalubridade;
    }

    public BigDecimal getValorValeTransporte() {
        return valorValeTransporte;
    }

    public BigDecimal getValorValeAlimentacaoDiario() {
        return valorValeAlimentacaoDiario;
    }

    public int getDiasTrabalhadosNoMes() {
        return diasTrabalhadosNoMes;
    }
}
