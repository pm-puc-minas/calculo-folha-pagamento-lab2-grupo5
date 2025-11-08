package folha_de_pagamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import folha_de_pagamento.model.imposto.IRRF;
import folha_de_pagamento.repository.IRRFRepository;

@Service
public class IRRFService {

    @Autowired
    private IRRFRepository irrfRepository;

    public IRRF adicionarIRRF(IRRF irrf) {
        return irrfRepository.save(irrf);
    }

    public IRRF obterIRRFPorId(Long id) {
        return irrfRepository.findById(id).orElse(null);
    }

    public void deletarIRRF(Long id) {
        irrfRepository.deleteById(id);
    }
    
}
