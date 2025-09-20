package folha_de_pagamento.model;
public class Gestor extends UsuarioDoSistema {

    public Gestor(String login, String senha) {
        super(login, senha);
    }

    public Funcionario registrarFuncionario(Funcionario funcionario) { 
        return funcionario; 
    }

    public Relatorio gerarFolhaPgt(Funcionario funcionario) { return new Relatorio(); }

}
