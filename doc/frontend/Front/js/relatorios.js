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

        let totalSalarios = 0;
        let totalINSS = 0;
        let totalFGTS = 0;
        let totalValeTransporteCusto = 0;
        let totalValeAlimentacao = 0;

        // Calcular todos os gastos
        fs.forEach(f => {
            const salario = f.salarioBruto || 0;
            const diasTrabalhados = f.diasTrabalhadosNoMes || 22;
            
            totalSalarios += salario;
            
            // INSS Empresa (20% sobre salário bruto)
            totalINSS += salario * 0.20;
            
            // FGTS (8% sobre salário bruto)
            totalFGTS += salario * 0.08;
            
            // Vale Transporte: custo empresa = VT total - desconto funcionário (máx 6%)
            const vtTotal = (f.valorValeTransporte || 0) * diasTrabalhados;
            const seisPercent = salario * 0.06;
            const descontoFuncionario = Math.min(vtTotal, seisPercent);
            totalValeTransporteCusto += vtTotal - descontoFuncionario;
            
            // Vale Alimentação (empresa paga 100%, sem desconto)
            totalValeAlimentacao += (f.valorValeAlimentacaoDiario || 0) * diasTrabalhados;
        });

        const totalGeral = totalSalarios + totalINSS + totalFGTS + totalValeTransporteCusto + totalValeAlimentacao;

        c.innerHTML = `
            <div class="stats-overview">
                <div class="stat-card stat-primary">
                    <div class="stat-icon"><i class="fa-solid fa-users"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Colaboradores</p>
                        <h3 class="stat-value">${fs.length}</h3>
                    </div>
                </div>
                
                <div class="stat-card stat-success">
                    <div class="stat-icon"><i class="fa-solid fa-sack-dollar"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Folha de Salários</p>
                        <h3 class="stat-value">R$ ${totalSalarios.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h3>
                    </div>
                </div>
            </div>

            <div class="expenses-grid">
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-shield-halved"></i>
                        <span>INSS Empresa</span>
                    </div>
                    <p class="expense-percentage">20% sobre salário</p>
                    <h4 class="expense-value">R$ ${totalINSS.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-piggy-bank"></i>
                        <span>FGTS</span>
                    </div>
                    <p class="expense-percentage">8% sobre salário</p>
                    <h4 class="expense-value">R$ ${totalFGTS.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-bus"></i>
                        <span>Vale Transporte (Custo Empresa)</span>
                    </div>
                    <p class="expense-percentage">Total VT - Desconto Funcionário</p>
                    <h4 class="expense-value">R$ ${totalValeTransporteCusto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-utensils"></i>
                        <span>Vale Alimentação</span>
                    </div>
                    <p class="expense-percentage">Benefício mensal</p>
                    <h4 class="expense-value">R$ ${totalValeAlimentacao.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
            </div>
            
            <div class="total-card">
                <div class="total-content">
                    <i class="fa-solid fa-chart-line total-icon"></i>
                    <div>
                        <p class="total-label">Custo Total Mensal da Empresa</p>
                        <h2 class="total-value">R$ ${totalGeral.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h2>
                    </div>
                </div>
            </div>`;
    } catch (error) {
        console.error('Erro:', error);
        c.innerHTML = '<p>Erro ao carregar relatório.</p>';
    }
}
gerar();