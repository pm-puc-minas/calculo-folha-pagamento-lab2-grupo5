package folha_de_pagamento.model;
public class UsuarioDoSistema {
    private String login;
    private String senha;

    public boolean fazerLogin(){ return true; }

    public void desconectar(){}

    public UsuarioDoSistema() {}

    public UsuarioDoSistema(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
