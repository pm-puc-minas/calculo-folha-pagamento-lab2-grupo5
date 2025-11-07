package folha_de_pagamento.controller;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public Funcionario adicionarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.adicionarFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> obterTodosFuncionarios() {
        return funcionarioService.obterTodosFuncionarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> obterFuncionarioPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.obterFuncionarioPorId(id);
        return funcionario.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) {
        try {
            Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionarioDetails);
            return ResponseEntity.ok(funcionarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}