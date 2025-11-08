package folha_de_pagamento.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import folha_de_pagamento.model.imposto.FGTS;
import folha_de_pagamento.model.imposto.INSS;
import folha_de_pagamento.model.imposto.IRRF;
import folha_de_pagamento.services.FGTSService;
import folha_de_pagamento.services.INSSService;
import folha_de_pagamento.services.IRRFService;

@Controller
@RequestMapping("/api/imposto")
public class ImpostoController {
    
    @Autowired
    private FGTSService fgtsService;
    private INSSService inssService;
    private IRRFService irrfService;

    @GetMapping("/fgts/{id}")
    public ResponseEntity<FGTS> obterFGTS(@PathVariable Long id) {
        try {
            FGTS fgtsPorId = fgtsService.obterFGTSPorId(id);
            return ResponseEntity.ok(fgtsPorId);
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @GetMapping("/inss/{id}")
    public ResponseEntity<INSS> obterINSS(@PathVariable Long id) {
        try {
            INSS INSSPorId = inssService.obterINSSPorId(id);
            return ResponseEntity.ok(INSSPorId);
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/irrf/{id}")
    public ResponseEntity<IRRF> obterIRRF(@PathVariable Long id) {
        try {
            IRRF IRRFPorId = irrfService.obterIRRFPorId(id);
            return ResponseEntity.ok(IRRFPorId);
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/fgts/cadastrar")
    public ResponseEntity<FGTS> adicionarFGTS(FGTS fgts) {
        try {
            FGTS novoFGTS = fgtsService.adicionarFGTS(fgts);
            return ResponseEntity.ok(novoFGTS);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    @PostMapping("/inss/cadastrar")
    public ResponseEntity<INSS> adicionarINSS(INSS inss) {
        try {
            INSS novoINSS = inssService.adicionarINSS(inss);
            return ResponseEntity.ok(novoINSS);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/irrf/cadastrar")
    public ResponseEntity<IRRF> adicionarIRRF(IRRF irrf) {
        try {
            IRRF novoIRRF = irrfService.adicionarIRRF(irrf);
            return ResponseEntity.ok(novoIRRF);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/fgts/{id}")
    public ResponseEntity<Void> deletarFGTS(@PathVariable Long id) {
        try {
            fgtsService.deletarFGTS(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/inss/{id}")
    public ResponseEntity<Void> deletarINSS(@PathVariable Long id) {
        try {
            inssService.deletarINSS(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/irrf/{id}")
    public ResponseEntity<Void> deletarIRRF(@PathVariable Long id) {
        try {
            irrfService.deletarIRRF(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}
