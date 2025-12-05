function requireAuth() {
  const token = sessionStorage.getItem('token');
  const role = sessionStorage.getItem('role');
  const funcionarioId = sessionStorage.getItem('funcionarioId');
  if (!token || role !== 'FUNCIONARIO' || !funcionarioId) {
    window.location.href = 'login.html';
  }
  return { token, role, funcionarioId: String(funcionarioId) };
}

const { funcionarioId } = requireAuth();
const c = document.getElementById('relatorioContainer');
const API_URL = 'http://localhost:8080/api/relatorios';
const API_FUNC = 'http://localhost:8080/api/funcionarios';

async function gerar() {
    try {
        // Buscar dados do funcionário
        const respFunc = await fetch(`${API_FUNC}/${funcionarioId}`);
        if (!respFunc.ok) throw new Error('Erro ao carregar seus dados');
        const func = await respFunc.json();
        
        // Buscar todos os relatórios e filtrar os do funcionário
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Erro ao carregar relatórios');
        const todosRelatorios = await response.json();
        
        const meusRelatorios = todosRelatorios.filter(r => 
            r.funcionario && r.funcionario.id == funcionarioId
        );

        if (meusRelatorios.length === 0) {
            c.innerHTML = `
                <div class="empty-state">
                    <i class="fa-solid fa-inbox"></i>
                    <h3>Nenhum relatório encontrado</h3>
                    <p>Seus relatórios de pagamento aparecerão aqui quando forem gerados.</p>
                </div>`;
            return;
        }

        const salario = func.salarioBruto || 0;
        const diasTrabalhados = func.diasTrabalhadosNoMes || 22;
        
        // Calcular médias dos relatórios
        let totalSalarioLiquido = 0;
        let totalINSS = 0;
        let totalIRRF = 0;
        
        meusRelatorios.forEach(r => {
            totalSalarioLiquido += r.salarioLiquido || 0;
            totalINSS += r.valorInss || 0;
            totalIRRF += r.valorIrrf || 0;
        });
        
        const mediaSalarioLiquido = totalSalarioLiquido / meusRelatorios.length;
        
        // Vale Transporte e Alimentação
        const valeTransporteBruto = (func.valorValeTransporte || 0) * diasTrabalhados;
        const valeAlimentacao = (func.valorValeAlimentacaoDiario || 0) * diasTrabalhados;

        c.innerHTML = `
            <div class="stats-overview">
                <div class="stat-card stat-primary">
                    <div class="stat-icon"><i class="fa-solid fa-file-lines"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Total de Relatórios</p>
                        <h3 class="stat-value">${meusRelatorios.length}</h3>
                    </div>
                </div>
                
                <div class="stat-card stat-success">
                    <div class="stat-icon"><i class="fa-solid fa-sack-dollar"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Salário Bruto Atual</p>
                        <h3 class="stat-value">R$ ${salario.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h3>
                    </div>
                </div>
                
                <div class="stat-card stat-info">
                    <div class="stat-icon"><i class="fa-solid fa-wallet"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Média Salário Líquido</p>
                        <h3 class="stat-value">R$ ${mediaSalarioLiquido.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h3>
                    </div>
                </div>
            </div>

            <div class="expenses-grid">
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-shield-halved"></i>
                        <span>INSS Total Descontado</span>
                    </div>
                    <p class="expense-percentage">Soma de todos os períodos</p>
                    <h4 class="expense-value">R$ ${totalINSS.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-receipt"></i>
                        <span>IRRF Total Descontado</span>
                    </div>
                    <p class="expense-percentage">Soma de todos os períodos</p>
                    <h4 class="expense-value">R$ ${totalIRRF.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-bus"></i>
                        <span>Vale Transporte Mensal</span>
                    </div>
                    <p class="expense-percentage">Benefício atual</p>
                    <h4 class="expense-value">R$ ${valeTransporteBruto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-utensils"></i>
                        <span>Vale Alimentação Mensal</span>
                    </div>
                    <p class="expense-percentage">Benefício atual</p>
                    <h4 class="expense-value">R$ ${valeAlimentacao.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
            </div>
            
            <div class="relatorios-lista">
                <h3><i class="fa-solid fa-history"></i> Histórico de Pagamentos</h3>
                <div class="table-container">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Data</th>
                                <th>Salário Bruto</th>
                                <th>INSS</th>
                                <th>IRRF</th>
                                <th>FGTS</th>
                                <th>Salário Líquido</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${meusRelatorios.map(r => `
                                <tr>
                                    <td>${r.date || '-'}</td>
                                    <td>R$ ${(r.salarioBruto || 0).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</td>
                                    <td>R$ ${(r.valorInss || 0).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</td>
                                    <td>R$ ${(r.valorIrrf || 0).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</td>
                                    <td>R$ ${(r.valorFgts || 0).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</td>
                                    <td class="destaque">R$ ${(r.salarioLiquido || 0).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</td>
                                </tr>
                            `).join('')}
                        </tbody>
                    </table>
                </div>
            </div>`;
    } catch (error) {
        console.error('Erro:', error);
        c.innerHTML = `<div class="error-message"><i class="fa-solid fa-exclamation-triangle"></i> ${error.message}</div>`;
    }
}

gerar();
