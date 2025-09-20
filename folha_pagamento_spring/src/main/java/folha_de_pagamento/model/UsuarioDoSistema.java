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

        boolean loginValido = (!this.login.isEmpty() && !this.senha.isEmpty()) && senhaValida;

        try {
            if (loginExiste && senhaValida) {
                System.out.println("Senha válida");
            } else {
                throw new Exception("Senha inválida, precisa ter no minimo 6 caracteres");
            }
        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
        }

        if (!loginValido)
            return false;

        return true;

    }

    public void desconectar() {
    }
}
