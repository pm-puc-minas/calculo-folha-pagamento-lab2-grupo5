package folha_de_pagamento.controller;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @PostMapping
    public Relatorio adicionarRelatorio(@RequestBody Relatorio relatorio) {
        return relatorioService.adicionarRelatorio(relatorio);
    }

    @GetMapping
    public List<Relatorio> obterTodosRelatorios() {
        return relatorioService.obterTodosRelatorios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> obterRelatorioPorId(@PathVariable Long id) {
        Optional<Relatorio> relatorio = relatorioService.obterRelatorioPorId(id);
        return relatorio.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> atualizarRelatorio(@PathVariable Long id, @RequestBody Relatorio relatorioDetails) {
        try {
            Relatorio relatorioAtualizado = relatorioService.atualizarRelatorio(id, relatorioDetails);
            return ResponseEntity.ok(relatorioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable Long id) {
        relatorioService.deletarRelatorio(id);
        return ResponseEntity.noContent().build();
    }
}