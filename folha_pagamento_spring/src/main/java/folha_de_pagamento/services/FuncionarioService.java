package folha_de_pagamento.services;

import folha_de_pagamento.model.user.Funcionario;
import folha_de_pagamento.model.user.Role;
import folha_de_pagamento.model.user.UsuarioDoSistema;
import folha_de_pagamento.repository.FuncionarioRepository;
import folha_de_pagamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        Funcionario salvo = funcionarioRepository.save(funcionario);
        // criar usuário padrão vinculado ao funcionário
        UsuarioDoSistema usuario = new UsuarioDoSistema();
        usuario.setLogin(salvo.getNome());
        usuario.setSenha("brasil2025");
        usuario.setRole(Role.FUNCIONARIO);
        usuario.setFuncionario(salvo);
        usuarioRepository.save(usuario);
        return salvo;
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

    @Transactional
    public void deletarFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));

        UsuarioDoSistema usuario = funcionario.getUsuarioDoSistema();

        funcionarioRepository.delete(funcionario);
    }
}