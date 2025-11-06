package folha_de_pagamento.model.user;

import java.util.ArrayList;
import java.util.List;

import folha_de_pagamento.model.Relatorio;
import jakarta.persistence.*;

@Entity
@Table(name = "gestores")
public class Gestor extends UsuarioDoSistema {

    private String departamento;
    
    @OneToMany(mappedBy = "gestor", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios = new ArrayList<>();

    @Transient
    private ArrayList<Relatorio> relatorios = new ArrayList<>();

    public Gestor() {}

    public Gestor(String login, String senha) {
        super(login, senha);
    }

    public Gestor(String login, String senha, String departamento) {
        super(login, senha);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<Relatorio> getRelatorios() {
        return this.relatorios;
    }

    public void registrarFuncionario(Funcionario funcionario) { 
        funcionarios.add(funcionario);
        funcionario.setGestor(this);
    }

    public Relatorio gerarFolhaPgt(Funcionario funcionario) { 
        return new Relatorio(); 
    }

    public void gerarRelatorio(Relatorio relatorio) {
		relatorios.add(relatorio);
	}
}
