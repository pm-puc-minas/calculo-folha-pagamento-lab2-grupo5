package folha_de_pagamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import folha_de_pagamento.model.imposto.INSS;
import folha_de_pagamento.repository.INSSRepository;

@Service
public class INSSService {

    @Autowired
    private INSSRepository inssRepository;

    public INSS adicionarINSS(INSS inss) {
        return inssRepository.save(inss);
    }

    public INSS obterINSSPorId(Long id) {
        return inssRepository.findById(id).orElse(null);
    }

    public void deletarINSS(Long id) {
        inssRepository.deleteById(id);
    }
    
}
