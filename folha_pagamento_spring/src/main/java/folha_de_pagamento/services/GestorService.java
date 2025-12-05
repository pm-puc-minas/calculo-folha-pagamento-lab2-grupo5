package folha_de_pagamento.services;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Gestor;
import folha_de_pagamento.repository.FuncionarioRepository;
import folha_de_pagamento.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;

    public Gestor adicionarGestor(Gestor gestor) {
        return gestorRepository.save(gestor);
    }

    public List<Gestor> obterTodosGestores() {
        return gestorRepository.findAll();
    }

    public Optional<Gestor> obterGestorPorId(Long id) {
        return gestorRepository.findById(id);
    }

    public Gestor atualizarGestor(Long id, Gestor gestorDetails) {
        Gestor gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado com id: " + id));

        gestor.setLogin(gestorDetails.getLogin());
        gestor.setSenha(gestorDetails.getSenha());
        gestor.setDepartamento(gestorDetails.getDepartamento());

        return gestorRepository.save(gestor);
    }

    public void deletarGestor(Long id) {
        gestorRepository.deleteById(id);
    }
}