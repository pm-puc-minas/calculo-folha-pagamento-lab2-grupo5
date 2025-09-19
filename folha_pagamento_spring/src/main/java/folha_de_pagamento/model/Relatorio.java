package folha_de_pagamento.model;

import java.math.BigDecimal;

import folha_de_pagamento.model.enums.GrauInsalubridade;

public class Relatorio {

    private INSS valorINSS;
    private FGTS valorFGTS;
    private IRRF valorIRRF;

    private Transporte valorTransporte;
    private Alimentacao valorAlimentacao;

    public Relatorio() {
    }

    public Relatorio(INSS valorINSS, FGTS valorFGTS, IRRF valorIRRF, Transporte valorTransporte,
            Alimentacao valorAlimentacao) {
        this.valorINSS = valorINSS;
        this.valorFGTS = valorFGTS;
        this.valorIRRF = valorIRRF;
        this.valorTransporte = valorTransporte;
        this.valorAlimentacao = valorAlimentacao;
    }

    public BigDecimal calcularSalarioHora(Funcionario funcionario) {

        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        int horasTrabalhadasPorDia = funcionario.getHorasTrabalhadasPorDia();
        int diasTrabalhadosNoMes = funcionario.getDiasTrabalhadosNoMes();

        BigDecimal horasTrabalhadasNoMes = BigDecimal.valueOf(horasTrabalhadasPorDia)
                .multiply(BigDecimal.valueOf(diasTrabalhadosNoMes));

        BigDecimal salarioHora = salarioBruto.divide(horasTrabalhadasNoMes, 2, BigDecimal.ROUND_HALF_UP);
        return salarioHora;

    }

    public BigDecimal calcularPericulosidade(Funcionario funcionario, boolean possuiPericulosidade) {

        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        if (possuiPericulosidade) {
            BigDecimal valorPericulosidade = salarioBruto.multiply(BigDecimal.valueOf(0.30)).setScale(2,
                    BigDecimal.ROUND_HALF_UP);
            return salarioBruto.add(valorPericulosidade);
        }
        return null;
    }

    public BigDecimal calcularInsalubridade(Funcionario funcionario, boolean possuiInsalubridade) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();

        if (possuiInsalubridade) {
            BigDecimal valorInsalubridade;

            if (funcionario.getGrauInsalubridade() == GrauInsalubridade.BAIXO) {
                valorInsalubridade = salarioBruto.multiply(BigDecimal.valueOf(0.10)).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                return salarioBruto.add(valorInsalubridade);
            }

            if (funcionario.getGrauInsalubridade() == GrauInsalubridade.MEDIO) {
                valorInsalubridade = salarioBruto.multiply(BigDecimal.valueOf(0.20)).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                return salarioBruto.add(valorInsalubridade);
            }

            if (funcionario.getGrauInsalubridade() == GrauInsalubridade.ALTO) {
                valorInsalubridade = salarioBruto.multiply(BigDecimal.valueOf(0.40)).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                return salarioBruto.add(valorInsalubridade);
            }

        }
        return null;
    }

    public void gerarRelatorio(Funcionario funcionario) {}
}
