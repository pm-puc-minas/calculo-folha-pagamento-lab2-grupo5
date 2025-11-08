// src/test/java/com/example/demo/service/FuncionarioServiceTest.java
package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.FuncionarioRepository;
import folha_de_pagamento.services.FuncionarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    private Gestor gestor;
    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        gestor = new Gestor();
        gestor.setId(1L);
        gestor.setNome("Gestor de Teste");

        funcionario = new Funcionario();
        funcionario.setNome("Funcionario Teste");
        funcionario.setSalario(5000.0);
        funcionario.setGestor(gestor);
    }

    @Test
    void testCadastrarFuncionario_Sucesso() {
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario salvo = funcionarioService.cadastrarFuncionario(funcionario);

        assertNotNull(salvo);
        assertEquals("Funcionario Teste", salvo.getNome());
        assertNotNull(salvo.getGestor());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void testCadastrarFuncionario_Falha_SemGestor() {
        funcionario.setGestor(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionarioService.cadastrarFuncionario(funcionario);
        });

        assertEquals("Não é possível cadastrar um funcionário sem um gestor associado.", exception.getMessage());
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }
}