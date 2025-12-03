package folha_de_pagamento.model.imposto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.enums.FaixaINSS;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class INSS extends Imposto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        BigDecimal contribuicaoTotal = BigDecimal.ZERO;

        for (FaixaINSS faixa : FaixaINSS.values()) {
            if (salarioBruto.compareTo(faixa.getPiso()) > 0) {
                BigDecimal baseDeCalculo = salarioBruto.min(faixa.getTeto());
                BigDecimal valorTributavelNaFaixa = baseDeCalculo.subtract(faixa.getPiso());
                contribuicaoTotal = contribuicaoTotal.add(valorTributavelNaFaixa.multiply(faixa.getAliquota()));
            }
        }

        return contribuicaoTotal.setScale(2, RoundingMode.HALF_UP);
    }
}