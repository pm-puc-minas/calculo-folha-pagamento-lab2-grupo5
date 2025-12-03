 
package folha_de_pagamento.testes;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.repository.RelatorioRepository;
import folha_de_pagamento.services.RelatorioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RelatorioServiceTest {

    @Mock
    private RelatorioRepository relatorioRepository;

    @InjectMocks
    private RelatorioService relatorioService;

    @Test
    void testAdicionarRelatorio_Sucesso() {
        Relatorio relatorio = new Relatorio();
        relatorio.setDate(LocalDate.now());
        relatorio.setSalarioBruto(new BigDecimal("5000.00"));
        relatorio.setValorInss(new BigDecimal("500.00"));
        relatorio.setValorIrrf(new BigDecimal("300.00"));
        relatorio.setValorFgts(new BigDecimal("400.00"));
        relatorio.setValorValeTransporte(new BigDecimal("150.00"));
        relatorio.setValorValeAlimentacao(new BigDecimal("300.00"));
        relatorio.setSalarioLiquido(new BigDecimal("4200.00"));

        when(relatorioRepository.save(any(Relatorio.class))).thenReturn(relatorio);

        Relatorio salvo = relatorioService.adicionarRelatorio(relatorio);
        assertNotNull(salvo);
        assertEquals(new BigDecimal("5000.00"), salvo.getSalarioBruto());
        verify(relatorioRepository, times(1)).save(any(Relatorio.class));
    }

    @Test
    void testAtualizarRelatorio_Sucesso() {
        Relatorio existente = new Relatorio();
        existente.setId(1L);
        existente.setDate(LocalDate.of(2025, 1, 1));
        existente.setSalarioBruto(new BigDecimal("4500.00"));

        Relatorio detalhes = new Relatorio();
        detalhes.setDate(LocalDate.of(2025, 2, 1));
        detalhes.setSalarioBruto(new BigDecimal("4700.00"));
        detalhes.setValorInss(new BigDecimal("470.00"));
        detalhes.setValorIrrf(new BigDecimal("280.00"));
        detalhes.setValorFgts(new BigDecimal("376.00"));
        detalhes.setValorValeTransporte(new BigDecimal("160.00"));
        detalhes.setValorValeAlimentacao(new BigDecimal("310.00"));
        detalhes.setSalarioLiquido(new BigDecimal("3950.00"));

        when(relatorioRepository.findById(1L)).thenReturn(java.util.Optional.of(existente));
        when(relatorioRepository.save(any(Relatorio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Relatorio atualizado = relatorioService.atualizarRelatorio(1L, detalhes);
        assertEquals(LocalDate.of(2025, 2, 1), atualizado.getDate());
        assertEquals(new BigDecimal("4700.00"), atualizado.getSalarioBruto());
        verify(relatorioRepository, times(1)).save(any(Relatorio.class));
    }
}
    