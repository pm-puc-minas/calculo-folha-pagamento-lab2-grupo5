const f = document.getElementById('formFuncionario');
const t = document.querySelector('#tabelaFuncionarios tbody');
const API_URL = 'http://localhost:8080/api/funcionarios';

// Função para buscar dados do Backend
async function render() {
    t.innerHTML = '';
    try {
        const response = await fetch(API_URL);
        const dados = await response.json();
        
        dados.forEach((x) => {
            t.innerHTML += `<tr>
                <td>${x.nome}</td>
                <td>${x.cargo}</td>
                <td>R$ ${x.salarioBruto.toFixed(2)}</td>
                <td><button onclick="remover(${x.id})">Excluir</button></td>
            </tr>`;
        });
    } catch (error) {
        console.error('Erro ao buscar funcionários:', error);
    }
}

// Cadastrar Funcionario
f.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    // Mapeando para o DTO esperado pelo Java
    const novo = {
        nome: document.getElementById('nome').value,
        cargo: document.getElementById('cargo').value,
        salarioBruto: parseFloat(document.getElementById('salario').value),
        // Adicionando valores padrão obrigatórios para o seu Model Java
        horasTrabalhadasPorDia: 8,
        diasTrabalhadosNoMes: 22,
        dependentes: 0,
        possuiPericulosidade: false,
        possuiInsalubridade: false,
        grauInsalubridade: null
    };

    try {
        const response = await fetch(`${API_URL}/cadastrar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novo)
        });

        if (response.ok) {
            f.reset();
            render(); // Recarrega a tabela
        } else {
            alert('Erro ao cadastrar funcionário');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Remover Funcionario
window.remover = async function(id) {
    if(confirm('Tem certeza?')) {
        try {
            await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            render();
        } catch (error) {
            console.error('Erro ao excluir:', error);
        }
    }
}

render();