// src/test/java/com/example/demo/service/GestorServiceTest.java
package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.GestorRepository;
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
        gestor.setNome("Novo Gestor");
        gestor.setEmail("gestor@teste.com");

        when(gestorRepository.save(any(Gestor.class))).thenReturn(gestor);

        Gestor salvo = gestorService.cadastrarGestor(gestor);

        assertNotNull(salvo);
        assertEquals("Novo Gestor", salvo.getNome());
        verify(gestorRepository, times(1)).save(gestor);
    }
}