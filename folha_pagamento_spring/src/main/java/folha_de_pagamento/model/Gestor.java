package folha_de_pagamento.model;

import java.util.ArrayList;

public class Gestor extends UsuarioDoSistema {

    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public Gestor(String login, String senha) {
        super(login, senha);
    }

    public void registrarFuncionario(Funcionario funcionario) { 
        funcionarios.add(funcionario);
    }

    public Relatorio gerarFolhaPgt(Funcionario funcionario) { return new Relatorio(); }

}
