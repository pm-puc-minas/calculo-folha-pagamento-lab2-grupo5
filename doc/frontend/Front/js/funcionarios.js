const f = document.getElementById('formFuncionario');
const t = document.querySelector('#tabelaFuncionarios tbody');
const API_URL = 'http://localhost:8080/api/funcionarios';

// Controle de modais
const modalCadastro = document.getElementById('modalCadastro');
const modalDetalhes = document.getElementById('modalDetalhes');
const btnNovo = document.getElementById('btnNovo');
const closeBtn = document.querySelector('.close');
const closeDetalhes = document.querySelector('.close-detalhes');
const insalubridadeCheck = document.getElementById('insalubridade');
const grauDiv = document.getElementById('grauInsalubridadeDiv');

// Abrir modal de cadastro
btnNovo.addEventListener('click', () => {
    modalCadastro.style.display = 'block';
});

// Fechar modais
closeBtn.addEventListener('click', () => {
    modalCadastro.style.display = 'none';
});

closeDetalhes.addEventListener('click', () => {
    modalDetalhes.style.display = 'none';
});

window.fecharModal = function() {
    modalCadastro.style.display = 'none';
}

// Fechar modal clicando fora
window.addEventListener('click', (e) => {
    if (e.target === modalCadastro) {
        modalCadastro.style.display = 'none';
    }
    if (e.target === modalDetalhes) {
        modalDetalhes.style.display = 'none';
    }
});

// Mostrar/ocultar grau de insalubridade
insalubridadeCheck.addEventListener('change', function() {
    grauDiv.style.display = this.checked ? 'block' : 'none';
    if (!this.checked) {
        document.getElementById('grauInsalubridade').value = '';
    }
});

// Função para buscar dados do Backend
async function render() {
    t.innerHTML = '';
    try {
        const response = await fetch(API_URL);
        const dados = await response.json();
        
        dados.forEach((x) => {
            t.innerHTML += `<tr>
                <td style="cursor: pointer;" onclick="verDetalhes(${x.id})">${x.nome}</td>
                <td>${x.cargo}</td>
                <td>R$ ${x.salarioBruto.toFixed(2)}</td>
                <td><button class="btn-delete" onclick="remover(${x.id})"><i class="fa-solid fa-trash"></i></button></td>
            </tr>`;
        });
    } catch (error) {
        console.error('Erro ao buscar funcionários:', error);
    }
}

// Ver detalhes do funcionário
window.verDetalhes = async function(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const func = await response.json();
        
        document.getElementById('detalhesConteudo').innerHTML = `
            <div class="detalhes-grid">
                <div class="detalhe-item">
                    <strong>Nome:</strong> ${func.nome}
                </div>
                <div class="detalhe-item">
                    <strong>Cargo:</strong> ${func.cargo}
                </div>
                <div class="detalhe-item">
                    <strong>Data de Admissão:</strong> ${func.dataAdmissao ? new Date(func.dataAdmissao).toLocaleDateString('pt-BR') : 'Não informada'}
                </div>
                <div class="detalhe-item">
                    <strong>Salário Bruto:</strong> R$ ${func.salarioBruto.toFixed(2)}
                </div>
                <div class="detalhe-item">
                    <strong>Horas/Dia:</strong> ${func.horasTrabalhadasPorDia}h
                </div>
                <div class="detalhe-item">
                    <strong>Dias/Mês:</strong> ${func.diasTrabalhadosNoMes}
                </div>
                <div class="detalhe-item">
                    <strong>Dependentes:</strong> ${func.dependentes}
                </div>
                <div class="detalhe-item">
                    <strong>Vale Transporte:</strong> R$ ${(func.valorValeTransporte || 0).toFixed(2)}/dia
                </div>
                <div class="detalhe-item">
                    <strong>Vale Alimentação:</strong> R$ ${(func.valorValeAlimentacaoDiario || 0).toFixed(2)}/dia
                </div>
                <div class="detalhe-item">
                    <strong>Periculosidade:</strong> ${func.possuiPericulosidade ? 'Sim (30%)' : 'Não'}
                </div>
                <div class="detalhe-item">
                    <strong>Insalubridade:</strong> ${func.possuiInsalubridade ? 
                        `Sim - ${func.grauInsalubridade} (${func.grauInsalubridade === 'BAIXO' ? '10%' : func.grauInsalubridade === 'MEDIO' ? '20%' : '40%'})` 
                        : 'Não'}
                </div>
            </div>
        `;
        modalDetalhes.style.display = 'block';
    } catch (error) {
        console.error('Erro ao buscar detalhes:', error);
        alert('Erro ao carregar detalhes do funcionário');
    }
}

// Cadastrar Funcionario
f.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const possuiInsalubridade = document.getElementById('insalubridade').checked;
    const grauInsalubridade = possuiInsalubridade ? document.getElementById('grauInsalubridade').value : null;
    
    if (possuiInsalubridade && !grauInsalubridade) {
        alert('Por favor, selecione o grau de insalubridade');
        return;
    }
    
    const novo = {
        nome: document.getElementById('nome').value,
        cargo: document.getElementById('cargo').value,
        dataAdmissao: document.getElementById('dataAdmissao').value || null,
        salarioBruto: parseFloat(document.getElementById('salario').value),
        horasTrabalhadasPorDia: parseInt(document.getElementById('horasDia').value),
        diasTrabalhadosNoMes: parseInt(document.getElementById('diasMes').value),
        dependentes: parseInt(document.getElementById('dependentes').value),
        valorValeTransporte: parseFloat(document.getElementById('valeTransporte').value),
        valorValeAlimentacaoDiario: parseFloat(document.getElementById('valeAlimentacao').value),
        possuiPericulosidade: document.getElementById('periculosidade').checked,
        possuiInsalubridade: possuiInsalubridade,
        grauInsalubridade: grauInsalubridade
    };

    try {
        const response = await fetch(`${API_URL}/cadastrar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novo)
        });

        if (response.ok) {
            f.reset();
            modalCadastro.style.display = 'none';
            render();
            alert('Funcionário cadastrado com sucesso!');
        } else {
            alert('Erro ao cadastrar funcionário');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
});

// Remover Funcionario
window.remover = async function(id) {
    if(confirm('Tem certeza que deseja excluir este funcionário?')) {
        try {
            await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            render();
        } catch (error) {
            console.error('Erro ao excluir:', error);
        }
    }
}

render();