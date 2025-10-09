package folha_de_pagamento.model;

import java.util.ArrayList;

public class Gestor extends UsuarioDoSistema {

    ArrayList<Funcionario> funcionarios = new ArrayList<>();
    ArrayList<Relatorio> relatorios = new ArrayList<>();

    public Gestor() {}

    public Gestor(String login, String senha) {
        super(login, senha);
    }

    public ArrayList<Relatorio> getRelatorios() {
        return this.relatorios;
    }

    public void registrarFuncionario(Funcionario funcionario) { 
        funcionarios.add(funcionario);
    }

    public Relatorio gerarFolhaPgt(Funcionario funcionario) { return new Relatorio(); }

    public void gerarRelatorio(Relatorio relatorio) {
		relatorios.add(relatorio);
	}


}
