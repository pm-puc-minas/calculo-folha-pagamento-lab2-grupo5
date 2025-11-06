package folha_de_pagamento.model.events;

import folha_de_pagamento.model.user.Funcionario;

public interface IEventoFuncionario {
    void onFuncionarioRegistrado(Funcionario funcionario);
}
