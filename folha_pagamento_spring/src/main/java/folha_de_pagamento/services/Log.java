package folha_de_pagamento.services;

import folha_de_pagamento.model.events.IEventoFuncionario;
import folha_de_pagamento.model.user.Funcionario;
import java.time.LocalDateTime;

/*
  Mostra no terminal quando um usuario for criado
  ao evento onFuncionarioRegistrado */

public class Log implements IEventoFuncionario {

    @Override
    public void onFuncionarioRegistrado(Funcionario funcionario) {
        System.out.println(
            "[LOG] " + LocalDateTime.now() + 
            " - Funcionário registrado: " + funcionario.getNome()
        );
    }
}