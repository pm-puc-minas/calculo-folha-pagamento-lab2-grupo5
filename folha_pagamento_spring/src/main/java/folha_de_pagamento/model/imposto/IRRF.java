package folha_de_pagamento.model.imposto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.enums.TabelaIRRF;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IRRF extends Imposto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final BigDecimal DEDUCAO_POR_DEPENDENTE = new BigDecimal("189.59");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BigDecimal calcularImposto(Funcionario funcionario) {
        INSS inss = new INSS();
        BigDecimal descontoINSS = inss.calcularImposto(funcionario);

        BigDecimal deducaoDependentes = DEDUCAO_POR_DEPENDENTE.multiply(new BigDecimal(funcionario.getNumeroDependentes()));

        BigDecimal baseDeCalculo = funcionario.getSalarioBruto().subtract(descontoINSS).subtract(deducaoDependentes);

        for (TabelaIRRF faixa : TabelaIRRF.values()) {
            if (baseDeCalculo.compareTo(faixa.getBaseMinima()) >= 0) {
                BigDecimal impostoCalculado = baseDeCalculo.multiply(faixa.getAliquota()).subtract(faixa.getParcelaADeduzir());
                return impostoCalculado.max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            }
        }

        return BigDecimal.ZERO;
    }
}