package folha_de_pagamento.model;

public class UsuarioDoSistema {
    private String login;
    private String senha;

    public UsuarioDoSistema() {
    }

    public UsuarioDoSistema(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public boolean fazerLogin() {

        boolean loginExiste = !this.login.isEmpty();
        boolean senhaValida = this.senha.length() >= 6;

        boolean loginValido = (loginExiste && !this.senha.isEmpty()) && senhaValida;

        if (!loginValido)
            return false;

        return true;

    }

    public boolean desconectar() {
        return false;
    }
}
