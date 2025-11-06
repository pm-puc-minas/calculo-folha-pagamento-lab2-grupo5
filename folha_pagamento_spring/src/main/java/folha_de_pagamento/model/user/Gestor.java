package folha_de_pagamento.model.user;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.events.IEventoFuncionario;
import folha_de_pagamento.model.events.IEventoRelatorio;

public class Gestor extends UsuarioDoSistema {
    
    private List<Funcionario> funcionarios;
    private List<Relatorio> relatorios;
    private List<IEventoFuncionario> listenersFuncionario = new ArrayList<>();
    private List<IEventoRelatorio> listenersFolha = new ArrayList<>();
    
    public Gestor() {}
    public Gestor(String login, String senha) {
        super(login, senha);
    }

    public List<Relatorio> listarRelatorios() {
        return this.relatorios;
    }
    private void gerarRelatorio(Relatorio relatorio) {
        relatorios.add(relatorio);
    }

    public void registrarFuncionario(Funcionario funcionario) { 
        funcionarios.add(funcionario);
        this.notificacaoFuncionario(funcionario);
    }

    public Relatorio gerarRelatorio(Funcionario funcionario) { 
        Relatorio relatorio = new Relatorio(LocalDate.now());
        this.gerarRelatorio(relatorio);
        this.notificacaoFolha(funcionario, relatorio);
        return relatorio;
    }

    private void notificacaoFolha(Funcionario funcionario, Relatorio relatorio) {
        for (IEventoRelatorio listener : listenersFolha) {
            listener.onFolhaGerada(funcionario, relatorio);
        }
    }

    private void notificacaoFuncionario(Funcionario funcionario) {
        for (IEventoFuncionario listener : listenersFuncionario) {
            listener.onFuncionarioRegistrado(funcionario);
        }
    }

    /* Gestor não precisa lidar com os eventos de funcionario ou folha, 
        porém é capaz de triggar, "avisar" ou publica-los. Permitem que um
        objeto notifique outros sobre uma mudança de estado sem que o 
        precise conhecer as classes concretas. */

    public void addFuncionarioListener(IEventoFuncionario listener) {
        listenersFuncionario.add(listener);
    }

    public void addFolhaPagamentoListener(IEventoRelatorio listener) {
        listenersFolha.add(listener);
    }
    


    public List<Funcionario> getFuncionariosComSalarioAcimaDe(BigDecimal valorMinimo) {
        return funcionarios.stream()
                .filter(f -> f.getSalarioBruto().compareTo(valorMinimo) > 0)
                .collect(Collectors.toList());
    }

    public List<String> getNomesDosFuncionarios() {
        return funcionarios.stream()
                .map(Funcionario::getNome) 
                .collect(Collectors.toList());
    }

}
