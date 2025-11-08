// src/main/java/com/example/demo/controller/GestorController.java
package folha_de_pagamento.controller;

import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.services.GestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestores")
public class GestorController {

    @Autowired
    private GestorService gestorService;

    @PostMapping
    public Gestor adicionarGestor(@RequestBody Gestor gestor) {
        return gestorService.adicionarGestor(gestor);
    }

    @GetMapping
    public List<Gestor> obterTodosGestores() {
        return gestorService.obterTodosGestores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gestor> obterGestorPorId(@PathVariable Long id) {
        Optional<Gestor> gestor = gestorService.obterGestorPorId(id);
        return gestor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestor> atualizarGestor(@PathVariable Long id, @RequestBody Gestor gestorDetails) {
        try {
            Gestor gestorAtualizado = gestorService.atualizarGestor(id, gestorDetails);
            return ResponseEntity.ok(gestorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGestor(@PathVariable Long id) {
        gestorService.deletarGestor(id);
        return ResponseEntity.noContent().build();
    }
}