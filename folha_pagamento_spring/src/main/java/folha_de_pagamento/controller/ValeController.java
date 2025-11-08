package folha_de_pagamento.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import folha_de_pagamento.model.vale.Alimentacao;
import folha_de_pagamento.model.vale.Transporte;
import folha_de_pagamento.services.AlimentacaoService;
import folha_de_pagamento.services.TransporteService;

@Controller
@RequestMapping("/api/vale")
public class ValeController {

    @Autowired
    private AlimentacaoService alimentacaoService;
    private TransporteService transporteService;

    @GetMapping("/alimentacao/{id}")
    public ResponseEntity<Alimentacao> obterAlimentacao(@PathVariable Long id) {
        try {
            Alimentacao alimentacaoPorId = alimentacaoService.obterAlimentacaoPorId(id);
            return ResponseEntity.ok(alimentacaoPorId);
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/transporte/{id}")
    public ResponseEntity<Transporte> obterTransporte(@PathVariable Long id) {
        try {
            Transporte transportePorId = transporteService.obterTransportePorId(id);
            return ResponseEntity.ok(transportePorId);
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/alimentacao/cadastrar")
    public ResponseEntity<Alimentacao> adicionarAlimentacao(Alimentacao alimentacao) {
        try {
            Alimentacao novoAlimentacao = alimentacaoService.adicionarAlimentacao(alimentacao);
            return ResponseEntity.ok(novoAlimentacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/transporte/cadastrar")
    public ResponseEntity<Transporte> adicionarTransporte(Transporte transporte) {
        try {
            Transporte novoTransporte = transporteService.adicionarTransporte(transporte);
            return ResponseEntity.ok(novoTransporte);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/alimentacao/{id}")
    public ResponseEntity<Alimentacao> atualizarAlimentacao(@PathVariable Long id,
            @RequestBody Alimentacao alimentacaoDetails) {
        try {
            Alimentacao alimentacaoAtualizado = alimentacaoService.atualizarAlimentacao(id, alimentacaoDetails);
            return ResponseEntity.ok(alimentacaoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/transporte/{id}")
    public ResponseEntity<Transporte> atualizarTransporte(@PathVariable Long id,
            @RequestBody Transporte transporteDetails) {
        try {
            Transporte transporteAtualizado = transporteService.atualizarTransporte(id, transporteDetails);
            return ResponseEntity.ok(transporteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/alimentacao/{id}")
    public ResponseEntity<Void> deletarAlimentacao(@PathVariable Long id) {
        try {
            alimentacaoService.deletarAlimentacao(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/transporte/{id}")
    public ResponseEntity<Void> deletarTransporte(@PathVariable Long id) {
        try {
            transporteService.deletarTransporte(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}

//joao heleno esteve aqui
