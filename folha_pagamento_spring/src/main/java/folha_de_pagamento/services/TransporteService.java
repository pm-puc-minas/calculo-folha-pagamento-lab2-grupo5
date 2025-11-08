package folha_de_pagamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import folha_de_pagamento.model.vale.Transporte;
import folha_de_pagamento.repository.TransporteRepository;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public Transporte adicionarTransporte(Transporte transporte) {
        return transporteRepository.save(transporte);
    }

    public Transporte obterTransportePorId(Long id) {
        return transporteRepository.findById(id).orElse(null);
    }

    public void deletarTransporte(Long id) {
        transporteRepository.deleteById(id);
    }

    public Transporte atualizarTransporte(Long id, Transporte transporteAtualizado) {
        return transporteRepository.findById(id).map(transporte -> {
            transporte.setValorTransporte(transporteAtualizado.getValorTransporte());
            return transporteRepository.save(transporte);
        }).orElse(null);
    }
}
