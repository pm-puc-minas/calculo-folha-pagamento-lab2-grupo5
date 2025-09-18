package folha_de_pagamento.model;
import java.time.LocalDate;

public class Trabalhador extends UsuarioDoSistema {
    private Funcionario funcionario;

    public Trabalhador() {}

    public Trabalhador(Funcionario funcionario, String login, String senha) {
        super(login, senha);
        this.funcionario = funcionario;
    }

    public Relatorio verContraCheque(LocalDate date) { return new Relatorio(); }

    public String verDescontos() { return ""; }
}
