package folha_de_pagamento.model.events;

import folha_de_pagamento.model.user.Funcionario;

@FunctionalInterface
public interface EventoFuncionario {
    void funcionarioRegistrado(Funcionario funcionario);
}