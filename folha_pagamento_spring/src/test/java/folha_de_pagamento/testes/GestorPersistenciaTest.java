package folha_de_pagamento.testes;

import folha_de_pagamento.DemoApplication;
import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.GestorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = DemoApplication.class)
class GestorPersistenciaTest {

    @Autowired
    private GestorRepository repository;

    @Test
    void testSalvarGestor() {
        Gestor gestor = new Gestor("admin_gestor_test", "senha123", "TI");
        Gestor salvo = repository.save(gestor);
        
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getLogin()).isEqualTo("admin_gestor_test");
    }

    @Test
    void testBuscarGestor() {
        Gestor gestor = new Gestor("gerente", "senha123", "RH");
        repository.save(gestor);
        
        var resultado = repository.findByLogin("gerente");
        
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getDepartamento()).isEqualTo("RH");
    }

    @Test
    void testAtualizarGestor() {
        Gestor gestor = new Gestor("chefe", "senha123", "Vendas");
        Gestor salvo = repository.save(gestor);
        
        salvo.setDepartamento("Comercial");
        repository.save(salvo);
        
        var atualizado = repository.findById(salvo.getId()).get();
        assertThat(atualizado.getDepartamento()).isEqualTo("Comercial");
    }

    @Test
    void testDeletarGestor() {
        Gestor gestor = new Gestor("temp", "senha123", "Temp");
        Gestor salvo = repository.save(gestor);
        
        repository.deleteById(salvo.getId());
        
        assertThat(repository.findById(salvo.getId())).isEmpty();
    }
}
