package folha_de_pagamento.model.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import folha_de_pagamento.model.events.IEventoRelatorio;
import folha_de_pagamento.model.events.IEventoFuncionario;

import folha_de_pagamento.model.Relatorio;

public class Gestor extends UsuarioDoSistema {
    
    private List<Funcionario> funcionarios;
    private List<Relatorio> relatorios;
    private List<IEventoFuncionario> listenersFuncionario = new ArrayList<>();
    private List<IEventoRelatorio> listenersFolha = new ArrayList<>();
    
    public Gestor() {}
    public Gestor(String login, String senha) {
        super(login, senha);
    }

    public List<Relatorio> getRelatorios() {
        return this.relatorios;
    }

    public void registrarFuncionario(Funcionario funcionario) { 
        funcionarios.add(funcionario);
    }

    public Relatorio gerarFolhaPgt(Funcionario funcionario) { 
        return new Relatorio();
    }

    public void gerarRelatorio(Relatorio relatorio) {
		relatorios.add(relatorio);
	}

    /* Gestor não precisa lidar com os eventos de funcionario ou folha, 
        porém é capaz de triggar, "avisar" ou publica-los. Eles são definidos 
        por interfaces. */

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
