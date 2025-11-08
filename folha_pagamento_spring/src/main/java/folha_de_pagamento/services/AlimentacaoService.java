package folha_de_pagamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import folha_de_pagamento.model.vale.Alimentacao;
import folha_de_pagamento.repository.AlimentacaoRepository;

@Service
public class AlimentacaoService {

    @Autowired
    private AlimentacaoRepository alimentacaoRepository;

    public Alimentacao adicionarAlimentacao(Alimentacao alimentacao) {
        return alimentacaoRepository.save(alimentacao);
    }

    public Alimentacao obterAlimentacaoPorId(Long id) {
        return alimentacaoRepository.findById(id).orElse(null);
    }

    public void deletarAlimentacao(Long id) {
        alimentacaoRepository.deleteById(id);
    }
    
    public Alimentacao atualizarAlimentacao(Long id, Alimentacao alimentacaoAtualizada) {
        return alimentacaoRepository.findById(id).map(alimentacao -> {
            alimentacao.setValorAlimentacao(alimentacaoAtualizada.getValorAlimentacao());
            return alimentacaoRepository.save(alimentacao);
        }).orElse(null);
    }
}
