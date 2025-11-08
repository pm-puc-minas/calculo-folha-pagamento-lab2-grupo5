package folha_de_pagamento.services;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> obterTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> obterFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioDetails) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));

        funcionario.setNome(funcionarioDetails.getNome());
        funcionario.setCargo(funcionarioDetails.getCargo());
        funcionario.setSalarioBruto(funcionarioDetails.getSalarioBruto());
        funcionario.setDataAdmissao(funcionarioDetails.getDataAdmissao());
        funcionario.setHorasTrabalhadasPorDia(funcionarioDetails.getHorasTrabalhadasPorDia());
        funcionario.setDependentes(funcionarioDetails.getDependentes());
        funcionario.setGrauInsalubridade(funcionarioDetails.getGrauInsalubridade());
        funcionario.setValorValeTransporte(funcionarioDetails.getValorValeTransporte());
        funcionario.setValorValeAlimentacaoDiario(funcionarioDetails.getValorValeAlimentacaoDiario());
        funcionario.setDiasTrabalhadosNoMes(funcionarioDetails.getDiasTrabalhadosNoMes());
        
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}