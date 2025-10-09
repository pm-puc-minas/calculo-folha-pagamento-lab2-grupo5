package folha_de_pagamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trabalhador extends UsuarioDoSistema {
    private Funcionario funcionario;

    public Trabalhador() {
    }

    public Trabalhador(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Trabalhador(Funcionario funcionario, String login, String senha) {
        super(login, senha);
        this.funcionario = funcionario;
    }

    public Relatorio verContraCheque(LocalDate date, Relatorio relatorio) {
        for(Relatorio r : relatorio.getRelatorios()) {
            if(date == relatorio.getDate()) {
                return r;
            }
        }
        return new Relatorio();
    }

    public BigDecimal verDescontos(Imposto imposto) {

        if (imposto instanceof INSS) {
            return imposto.calcularImposto(funcionario);
        }

        if (imposto instanceof IRRF) {
            return imposto.calcularImposto(funcionario);
        }

        if (imposto instanceof FGTS) {
            return imposto.calcularImposto(funcionario);
        }

        return new BigDecimal("0.00");
    }
}
