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
    private BigDecimal valorValeAlimentacao;
    private int diasTrabalhadosNoMes;

    public Funcionario() {
    }

    // Constructor utilizado para testes
    public Funcionario(BigDecimal salarioBruto, int horasTrabalhadasPorDia, 
                        int diasTrabalhadosNoMes) {
        this.salarioBruto = salarioBruto;
        this.horasTrabalhadasPorDia = horasTrabalhadasPorDia;
        this.diasTrabalhadosNoMes = diasTrabalhadosNoMes;
    }

    // Construtor para Alimentação
    public Funcionario(BigDecimal salarioBruto, int diasTrabalhadosNoMes, BigDecimal valorALimentacao) {
        this.salarioBruto = salarioBruto;
        this.diasTrabalhadosNoMes = diasTrabalhadosNoMes;
        this.valorValeAlimentacao = valorALimentacao;
    }

    public Funcionario(String nome, LocalDate dataAdmissão, String cargo, BigDecimal salarioBruto,
            int horasTrabalhadasPorDia, int dependentes, boolean possuiPericulosidade, boolean possuiInsalubridade,
            GrauInsalubridade grauInsalubridade, BigDecimal valorValeTransporte,
            BigDecimal valorValeAlimentacao, int diasTrabalhadosNoMes) {
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
        this.valorValeAlimentacao = valorValeAlimentacao;
        this.diasTrabalhadosNoMes = diasTrabalhadosNoMes;
    }

    public int getHorasTrabalhadasPorDia() {
        return horasTrabalhadasPorDia;
    }

    public int getDiasTrabalhadosNoMes() {
        return diasTrabalhadosNoMes;
    }

    public void setSalarioBruto(int num) {
        this.salarioBruto = new BigDecimal(num);
    }
    
    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public GrauInsalubridade getGrauInsalubridade() {
        return grauInsalubridade;
    }

    public BigDecimal getValorAlimentacao() {
        return valorValeAlimentacao;
    }

    public void setValorTransporte(int num) {
        this.valorValeTransporte = new BigDecimal(num);
    }

    public BigDecimal getValorTransporte() {
        return valorValeTransporte;
    }
}
