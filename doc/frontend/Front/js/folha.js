const tbody = document.querySelector('#tabelaFolha tbody');
const API_FUNC = 'http://localhost:8080/api/funcionarios';
const API_CALC = 'http://localhost:8080/api/calculos/folha';

document.getElementById('gerarFolha').addEventListener('click', async () => {
    tbody.innerHTML = '<tr><td colspan="5">Calculando... Aguarde.</td></tr>';
    
    try {
        // 1. Busca todos os funcionários
        const respFunc = await fetch(API_FUNC);
        const funcionarios = await respFunc.json();
        
        tbody.innerHTML = ''; // Limpa tabela

        // 2. Para cada funcionário, pede ao Backend o cálculo exato
        for (const func of funcionarios) {
            const respCalc = await fetch(`${API_CALC}/${func.id}`);
            
            if (respCalc.ok) {
                const dados = await respCalc.json();
                
                // O Backend retorna um Map com: salarioBruto, inss, irrf, salarioLiquidoEstimado
                tbody.innerHTML += `<tr>
                    <td>${func.nome}</td>
                    <td>R$ ${dados.salarioBruto.toFixed(2)}</td>
                    <td>R$ ${dados.inss.toFixed(2)}</td>
                    <td>R$ ${dados.irrf.toFixed(2)}</td>
                    <td><strong>R$ ${dados.salarioLiquidoEstimado.toFixed(2)}</strong></td>
                </tr>`;
            }
        }
    } catch (error) {
        console.error('Erro ao gerar folha:', error);
        tbody.innerHTML = '<tr><td colspan="5">Erro ao conectar com o servidor.</td></tr>';
    }
});