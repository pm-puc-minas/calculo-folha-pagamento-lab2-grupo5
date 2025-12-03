package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.GestorRepository;
import folha_de_pagamento.services.GestorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GestorServiceTest {

    @Mock
    private GestorRepository gestorRepository;

    @InjectMocks
    private GestorService gestorService;

    @Test
    void testCadastrarGestor() {
        Gestor gestor = new Gestor();
        gestor.setLogin("gestor.teste");
        gestor.setSenha("senha123");
        gestor.setDepartamento("Financeiro");

        when(gestorRepository.save(any(Gestor.class))).thenReturn(gestor);

        Gestor salvo = gestorService.adicionarGestor(gestor);

        assertNotNull(salvo);
        assertEquals("gestor.teste", salvo.getLogin());
        assertEquals("Financeiro", salvo.getDepartamento());
        verify(gestorRepository, times(1)).save(any(Gestor.class));
    }
}
    