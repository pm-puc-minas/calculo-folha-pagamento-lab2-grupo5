const c = document.getElementById('relatorioContainer');
const API_URL = 'http://localhost:8080/api/funcionarios';

async function gerar() {
    try {
        const response = await fetch(API_URL);
        const fs = await response.json();

        if (fs.length === 0) {
            c.innerHTML = '<p>Nenhum dado disponível.</p>';
            return;
        }

        const total = fs.reduce((a, f) => a + f.salarioBruto, 0);

        c.innerHTML = `
            <div class="card">
                <h3>Total de Funcionários: ${fs.length}</h3>
                <h3>Gasto Total Bruto (Base): R$ ${total.toFixed(2)}</h3>
                <p style="font-size:0.8em; color:#666; margin-top:10px;">*Dados vindos diretamente do MySQL</p>
            </div>`;
    } catch (error) {
        c.innerHTML = '<p>Erro ao carregar relatório.</p>';
    }
}
gerar();