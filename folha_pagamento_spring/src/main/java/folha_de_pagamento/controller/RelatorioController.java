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

    @PostMapping("/cadastrar")
    public ResponseEntity<Relatorio> adicionarRelatorio(@RequestBody Relatorio relatorio) {
        try {
            Relatorio novoRelatorio = relatorioService.adicionarRelatorio(relatorio);
            return ResponseEntity.ok(novoRelatorio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
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