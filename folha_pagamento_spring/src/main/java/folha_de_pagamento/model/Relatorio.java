package folha_de_pagamento.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import folha_de_pagamento.model.enums.GrauInsalubridade;
import folha_de_pagamento.model.user.Funcionario;
import jakarta.persistence.*;

@Entity
@Table(name = "relatorios")
public class Relatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	private LocalDate date;
	private BigDecimal salarioBruto;
	private BigDecimal valorInss;
	private BigDecimal valorIrrf;
	private BigDecimal valorFgts;
	private BigDecimal valorValeTransporte;
	private BigDecimal valorValeAlimentacao;
	private BigDecimal salarioLiquido;

	public Relatorio() {
	}

	public Relatorio(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public BigDecimal getValorInss() {
		return valorInss;
	}

	public void setValorInss(BigDecimal valorInss) {
		this.valorInss = valorInss;
	}

	public BigDecimal getValorIrrf() {
		return valorIrrf;
	}

	public void setValorIrrf(BigDecimal valorIrrf) {
		this.valorIrrf = valorIrrf;
	}

	public BigDecimal getValorFgts() {
		return valorFgts;
	}

	public void setValorFgts(BigDecimal valorFgts) {
		this.valorFgts = valorFgts;
	}

	public BigDecimal getValorValeTransporte() {
		return valorValeTransporte;
	}

	public void setValorValeTransporte(BigDecimal valorValeTransporte) {
		this.valorValeTransporte = valorValeTransporte;
	}

	public BigDecimal getValorValeAlimentacao() {
		return valorValeAlimentacao;
	}

	public void setValorValeAlimentacao(BigDecimal valorValeAlimentacao) {
		this.valorValeAlimentacao = valorValeAlimentacao;
	}

	public BigDecimal getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(BigDecimal salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	public BigDecimal calcularSalarioHora(Funcionario funcionario) {

		BigDecimal salarioBruto = funcionario.getSalarioBruto();
		int horasTrabalhadasPorDia = funcionario.getHorasTrabalhadasPorDia();
		int diasTrabalhadosNoMes = funcionario.getDiasTrabalhadosNoMes();

		BigDecimal horasTrabalhadasNoMes = BigDecimal.valueOf(horasTrabalhadasPorDia)
				.multiply(BigDecimal.valueOf(diasTrabalhadosNoMes));


		BigDecimal salarioHora = salarioBruto.divide(horasTrabalhadasNoMes, 2, RoundingMode.HALF_UP);
		return salarioHora;

	}

	public BigDecimal calcularPericulosidade(Funcionario funcionario, boolean possuiPericulosidade) {

		BigDecimal salarioBruto = funcionario.getSalarioBruto();
		if (possuiPericulosidade) {
	    	BigDecimal valorPericulosidade = salarioBruto.multiply(BigDecimal.valueOf(0.30)).setScale(2, RoundingMode.HALF_UP);
			return salarioBruto.add(valorPericulosidade);
		}
		return salarioBruto;
	}

	public BigDecimal calcularInsalubridade(Funcionario funcionario, boolean possuiInsalubridade) {
		BigDecimal salarioBruto = funcionario.getSalarioBruto();

		if (possuiInsalubridade) {
			BigDecimal valorInsalubridade;

			if (funcionario.getGrauInsalubridade() == GrauInsalubridade.BAIXO) {
				valorInsalubridade = salarioBruto.multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_UP);
				BigDecimal salarioTotal = salarioBruto.add(valorInsalubridade);
				return salarioTotal;
			}

			if (funcionario.getGrauInsalubridade() == GrauInsalubridade.MEDIO) {
				valorInsalubridade = salarioBruto.multiply(BigDecimal.valueOf(0.20)).setScale(2, RoundingMode.HALF_UP);

				BigDecimal salarioTotal = salarioBruto.add(valorInsalubridade);
				return salarioTotal;
			}

			if (funcionario.getGrauInsalubridade() == GrauInsalubridade.ALTO) {
				valorInsalubridade = salarioBruto.multiply(BigDecimal.valueOf(0.40)).setScale(2, RoundingMode.HALF_UP);

				BigDecimal salarioTotal = salarioBruto.add(valorInsalubridade);
				return salarioTotal;
			}

		}

		return salarioBruto;
	}
}
