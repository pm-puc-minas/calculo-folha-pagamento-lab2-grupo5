package folha_de_pagamento.testes;

import folha_de_pagamento.DemoApplication;
import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.FuncionarioRepository;
import folha_de_pagamento.repository.GestorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = DemoApplication.class)
class FuncionarioPersistenciaTest {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private GestorRepository gestorRepository;

    @Test
    void testSalvarFuncionario() {
        Funcionario func = new Funcionario("Joao Silva", new BigDecimal("3000.00"));
        Funcionario salvo = repository.save(func);
        
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Joao Silva");
    }

    @Test
    void testBuscarFuncionario() {
        Funcionario func = new Funcionario("Maria Santos", new BigDecimal("4500.00"));
        repository.save(func);
        
        var resultado = repository.findByNome("Maria Santos");
        
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getSalarioBruto()).isEqualByComparingTo("4500.00");
    }

    @Test
    void testAtualizarFuncionario() {
        Funcionario func = new Funcionario("Pedro Lima", new BigDecimal("3000.00"));
        func.setCargo("Junior");
        Funcionario salvo = repository.save(func);
        
        salvo.setSalarioBruto(new BigDecimal("3500.00"));
        salvo.setCargo("Pleno");
        repository.save(salvo);
        
        var atualizado = repository.findById(salvo.getId()).get();
        assertThat(atualizado.getSalarioBruto()).isEqualByComparingTo("3500.00");
        assertThat(atualizado.getCargo()).isEqualTo("Pleno");
    }

    @Test
    void testDeletarFuncionario() {
        Funcionario func = new Funcionario("Temp", new BigDecimal("2000.00"));
        Funcionario salvo = repository.save(func);
        
        repository.deleteById(salvo.getId());
        
        assertThat(repository.findById(salvo.getId())).isEmpty();
    }

    @Test
    void testFuncionarioComGestor() {
        Gestor gestor = gestorRepository.save(new Gestor("gestor1", "senha", "TI"));
        
        Funcionario func = new Funcionario("Carlos", new BigDecimal("5000.00"));
        func.setGestor(gestor);
        repository.save(func);
        
        var funcs = repository.findByGestorId(gestor.getId());
        assertThat(funcs).hasSize(1);
    }
}
