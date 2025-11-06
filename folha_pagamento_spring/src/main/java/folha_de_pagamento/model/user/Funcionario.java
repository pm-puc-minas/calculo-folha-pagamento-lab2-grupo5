package folha_de_pagamento.model.user;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.enums.GrauInsalubridade;
import jakarta.persistence.*;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    
    @Id
    private Long id;
    private String nome;
    private LocalDate dataAdmissao;
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
    
    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Gestor gestor;
    
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Relatorio> relatorios = new ArrayList<>();

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

    public Funcionario(String nome, LocalDate dataAdmissao, String cargo, BigDecimal salarioBruto,
            int horasTrabalhadasPorDia, int dependentes, boolean possuiPericulosidade, boolean possuiInsalubridade,
            GrauInsalubridade grauInsalubridade, BigDecimal valorValeTransporte,
            BigDecimal valorValeAlimentacaoDiario, int diasTrabalhadosNoMes) {
        this.nome = nome;
        this.dataAdmissao = dataAdmissao;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(BigDecimal salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public int getHorasTrabalhadasPorDia() {
        return horasTrabalhadasPorDia;
    }

    public void setHorasTrabalhadasPorDia(int horasTrabalhadasPorDia) {
        this.horasTrabalhadasPorDia = horasTrabalhadasPorDia;
    }

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }

    public boolean getPossuiPericulosidade() {
        return possuiPericulosidade;
    }

    public void setPossuiPericulosidade(boolean possuiPericulosidade) {
        this.possuiPericulosidade = possuiPericulosidade;
    }

    public int getNumeroDependentes() {
        return dependentes;
    }

    public boolean getPossuiInsalubridade() {
        return possuiInsalubridade;
    }

    public void setPossuiInsalubridade(boolean possuiInsalubridade) {
        this.possuiInsalubridade = possuiInsalubridade;
    }

    public GrauInsalubridade getGrauInsalubridade() {
        return grauInsalubridade;
    }

    public void setGrauInsalubridade(GrauInsalubridade grauInsalubridade) {
        this.grauInsalubridade = grauInsalubridade;
    }

    public BigDecimal getValorValeTransporte() {
        return valorValeTransporte;
    }

    public void setValorValeTransporte(BigDecimal valorValeTransporte) {
        this.valorValeTransporte = valorValeTransporte;
    }

    public BigDecimal getValorValeAlimentacaoDiario() {
        return valorValeAlimentacaoDiario;
    }

    public void setValorValeAlimentacaoDiario(BigDecimal valorValeAlimentacaoDiario) {
        this.valorValeAlimentacaoDiario = valorValeAlimentacaoDiario;
    }

    public int getDiasTrabalhadosNoMes() {
        return diasTrabalhadosNoMes;
    }

    public void setDiasTrabalhadosNoMes(int diasTrabalhadosNoMes) {
        this.diasTrabalhadosNoMes = diasTrabalhadosNoMes;
    }

    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public List<Relatorio> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<Relatorio> relatorios) {
        this.relatorios = relatorios;
    }
}
