package folha_de_pagamento.controller;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.imposto.FGTS;
import folha_de_pagamento.model.imposto.INSS;
import folha_de_pagamento.model.imposto.IRRF;
import folha_de_pagamento.model.vale.Transporte;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.repository.FuncionarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/calculos")
public class CalculoController {

    private final FuncionarioRepository funcionarioRepository;

    public CalculoController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    private Funcionario getFuncionarioOrThrow(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario id=" + id + " não encontrado"));
    }

    @GetMapping("/salario-hora/{id}")
    public ResponseEntity<Map<String, Object>> calcularSalarioHora(@PathVariable Long id) {
        Funcionario f = getFuncionarioOrThrow(id);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal salarioHora = r.calcularSalarioHora(f);
        return ResponseEntity.ok(Map.of(
                "funcionarioId", id,
                "salarioBruto", f.getSalarioBruto(),
                "salarioHora", salarioHora
        ));
    }

    @GetMapping("/periculosidade/{id}")
    public ResponseEntity<Map<String, Object>> calcularPericulosidade(@PathVariable Long id) {
        Funcionario f = getFuncionarioOrThrow(id);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularPericulosidade(f, f.getPossuiPericulosidade());
        return ResponseEntity.ok(Map.of(
                "funcionarioId", id,
                "possuiPericulosidade", f.getPossuiPericulosidade(),
                "salarioBruto", f.getSalarioBruto(),
                "salarioComPericulosidade", total
        ));
    }

    @GetMapping("/insalubridade/{id}")
    public ResponseEntity<Map<String, Object>> calcularInsalubridade(@PathVariable Long id) {
        Funcionario f = getFuncionarioOrThrow(id);
        Relatorio r = new Relatorio(LocalDate.now());
        BigDecimal total = r.calcularInsalubridade(f, f.getPossuiInsalubridade());
        return ResponseEntity.ok(Map.of(
                "funcionarioId", id,
                "grauInsalubridade", f.getGrauInsalubridade(),
                "possuiInsalubridade", f.getPossuiInsalubridade(),
                "salarioBruto", f.getSalarioBruto(),
                "salarioComInsalubridade", total
        ));
    }

    @GetMapping("/folha/{id}")
    public ResponseEntity<Map<String, Object>> calcularFolha(@PathVariable Long id) {
        Funcionario f = getFuncionarioOrThrow(id);
        Relatorio relatorio = new Relatorio(LocalDate.now());

        BigDecimal salarioBase = f.getSalarioBruto();
        BigDecimal salarioPericulosidade = relatorio.calcularPericulosidade(f, f.getPossuiPericulosidade());
        BigDecimal salarioInsalubridade = relatorio.calcularInsalubridade(f, f.getPossuiInsalubridade());

        INSS inss = new INSS();
        IRRF irrf = new IRRF();
        FGTS fgts = new FGTS();

        BigDecimal valorInss = inss.calcularImposto(f);
        BigDecimal valorIrrf = irrf.calcularImposto(f);
        BigDecimal valorFgts = fgts.calcularImposto(f);
        
        // Calcular vale transporte (até 6% do salário)
        double valorVTMensal = f.getValorValeTransporte().doubleValue() * f.getDiasTrabalhadosNoMes();
        Transporte transporte = new Transporte(valorVTMensal);
        BigDecimal valorValeTransporte = transporte.calcularVale(f);

        BigDecimal salarioBaseComAdicionais = Stream.of(
                salarioBase,
                f.getPossuiPericulosidade() ? salarioPericulosidade : null,
                f.getPossuiInsalubridade() ? salarioInsalubridade : null
        )
        .filter(Objects::nonNull)
        .max(Comparator.naturalOrder())
        .orElse(salarioBase);

        BigDecimal salarioLiquidoEstimado = salarioBaseComAdicionais
                .subtract(valorInss)
                .subtract(valorIrrf)
                .subtract(valorValeTransporte);

        return ResponseEntity.ok(Map.of(
                "funcionarioId", id,
                "salarioBruto", salarioBase,
                "salarioComPericulosidade", salarioPericulosidade,
                "salarioComInsalubridade", salarioInsalubridade,
                "inss", valorInss,
                "irrf", valorIrrf,
                "fgts", valorFgts,
                "valeTransporte", valorValeTransporte,
                "salarioLiquidoEstimado", salarioLiquidoEstimado
        ));
    }
}
