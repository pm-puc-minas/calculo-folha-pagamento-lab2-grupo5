package folha_de_pagamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import folha_de_pagamento.model.imposto.FGTS;
import folha_de_pagamento.repository.FGTSRepository;

@Service
public class FGTSService {

    @Autowired
    private FGTSRepository fgtsRepository;

    public FGTS adicionarFGTS(FGTS fgts) {
        return fgtsRepository.save(fgts);
    }

    public FGTS obterFGTSPorId(Long id) {
        return fgtsRepository.findById(id).orElse(null);
    }

    public void deletarFGTS(Long id) {
        fgtsRepository.deleteById(id);
    }
    
}
