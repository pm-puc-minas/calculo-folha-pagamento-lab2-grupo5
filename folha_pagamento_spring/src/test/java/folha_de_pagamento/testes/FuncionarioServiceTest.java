package folha_de_pagamento.testes;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.FuncionarioRepository;
import folha_de_pagamento.services.FuncionarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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

        funcionario = new Funcionario();
        funcionario.setNome("Funcionario Teste");
        funcionario.setGestor(gestor);
    }


}