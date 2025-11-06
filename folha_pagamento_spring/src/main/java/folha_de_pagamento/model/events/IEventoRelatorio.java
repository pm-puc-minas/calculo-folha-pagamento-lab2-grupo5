package folha_de_pagamento.model.events;

import folha_de_pagamento.model.Relatorio;
import folha_de_pagamento.model.user.Funcionario;

public interface IEventoRelatorio {
    void onFolhaGerada(Funcionario funcionario, Relatorio relatorio);
}
