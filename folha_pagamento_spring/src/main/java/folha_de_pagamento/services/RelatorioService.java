package folha_de_pagamento.services;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    public Relatorio adicionarRelatorio(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public List<Relatorio> obterTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    public Optional<Relatorio> obterRelatorioPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    public Relatorio atualizarRelatorio(Long id, Relatorio relatorioDetails) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com id: " + id));

        relatorio.setDate(relatorioDetails.getDate());
        relatorio.setSalarioBruto(relatorioDetails.getSalarioBruto());
        relatorio.setValorInss(relatorioDetails.getValorInss());
        relatorio.setValorIrrf(relatorioDetails.getValorIrrf());
        relatorio.setValorFgts(relatorioDetails.getValorFgts());
        relatorio.setValorValeTransporte(relatorioDetails.getValorValeTransporte());
        relatorio.setValorValeAlimentacao(relatorioDetails.getValorValeAlimentacao());
        relatorio.setSalarioLiquido(relatorioDetails.getSalarioLiquido());
        
        return relatorioRepository.save(relatorio);
    }

    public void deletarRelatorio(Long id) {
        relatorioRepository.deleteById(id);
    }
}