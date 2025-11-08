package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.repository.RelatorioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RelatorioServiceTest {

    @Mock
    private RelatorioRepository relatorioRepository;

    @InjectMocks
    private RelatorioService relatorioService;

    @Test
    void testCadastrarRelatorio_Sucesso() {

        Gestor gestor = new Gestor();
        gestor.setId(1L);
        
        Relatorio relatorio = new Relatorio();
        relatorio.setTitulo("Relatório de Teste");
        relatorio.setData(new Date());
        relatorio.setGestor(gestor);
        when(relatorioRepository.save(any(Relatorio.class))).thenReturn(relatorio);

        
        Relatorio salvo = relatorioService.cadastrarRelatorio(relatorio);
        assertNotNull(salvo);
        assertEquals("Relatório de Teste", salvo.getTitulo());
        verify(relatorioRepository, times(1)).save(relatorio);
    }

    @Test
    void testCadastrarRelatorio_Falha_SemGestor() {
        Relatorio relatorio = new Relatorio();
        relatorio.setTitulo("Relatório Sem Gestor");
        relatorio.setGestor(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            relatorioService.cadastrarRelatorio(relatorio);
        });

        assertEquals("Não é possível cadastrar um relatório sem um gestor associado.", exception.getMessage());
        verify(relatorioRepository, never()).save(any(Relatorio.class));
    }
}