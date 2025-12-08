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
const API_FUNC = 'http://localhost:8080/api/funcionarios';
const API_CALC = 'http://localhost:8080/api/calculos/folha';
const API_REL = 'http://localhost:8080/api/relatorios';

async function gerar() {
    c.innerHTML = '<div class="loading"><i class="fa-solid fa-spinner fa-spin"></i> Carregando seus dados...</div>';
    
    try {
        // Buscar dados do funcionário
        const respFunc = await fetch(`${API_FUNC}/${funcionarioId}`);
        if (!respFunc.ok) throw new Error('Erro ao carregar seus dados');
        const func = await respFunc.json();
        
        // Buscar cálculo da folha
        const respCalc = await fetch(`${API_CALC}/${funcionarioId}`);
        if (!respCalc.ok) throw new Error('Erro ao calcular dados');
        const calc = await respCalc.json();
        
        // Buscar relatórios salvos
        const respRel = await fetch(API_REL);
        const todosRelatorios = respRel.ok ? await respRel.json() : [];
        const meusRelatorios = todosRelatorios.filter(r => r.funcionario && r.funcionario.id == funcionarioId);

        const salario = func.salarioBruto || 0;
        const diasTrabalhados = func.diasTrabalhadosNoMes || 22;
        
        // Valores dos cálculos
        const salarioLiquido = calc.salarioLiquidoEstimado || 0;
        const inss = calc.inss || 0;
        const irrf = calc.irrf || 0;
        const fgts = calc.fgts || 0;
        
        // Vale Transporte e Alimentação
        const valeTransporteBruto = (func.valorValeTransporte || 0) * diasTrabalhados;
        const valeAlimentacao = (func.valorValeAlimentacaoDiario || 0) * diasTrabalhados;

        c.innerHTML = `
            <div class="stats-overview">
                <div class="stat-card stat-primary">
                    <div class="stat-icon"><i class="fa-solid fa-user"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Funcionário</p>
                        <h3 class="stat-value">${func.nome}</h3>
                    </div>
                </div>
                
                <div class="stat-card stat-success">
                    <div class="stat-icon"><i class="fa-solid fa-sack-dollar"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Salário Bruto</p>
                        <h3 class="stat-value">R$ ${salario.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h3>
                    </div>
                </div>
                
                <div class="stat-card stat-info">
                    <div class="stat-icon"><i class="fa-solid fa-wallet"></i></div>
                    <div class="stat-content">
                        <p class="stat-label">Salário Líquido</p>
                        <h3 class="stat-value">R$ ${salarioLiquido.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h3>
                    </div>
                </div>
            </div>

            <div class="expenses-grid">
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-shield-halved"></i>
                        <span>INSS Descontado</span>
                    </div>
                    <p class="expense-percentage">Desconto mensal</p>
                    <h4 class="expense-value">R$ ${inss.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-receipt"></i>
                        <span>IRRF Descontado</span>
                    </div>
                    <p class="expense-percentage">Desconto mensal</p>
                    <h4 class="expense-value">R$ ${irrf.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-piggy-bank"></i>
                        <span>FGTS (Empresa)</span>
                    </div>
                    <p class="expense-percentage">8% sobre salário</p>
                    <h4 class="expense-value">R$ ${fgts.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-building"></i>
                        <span>INSS Patronal (Empresa)</span>
                    </div>
                    <p class="expense-percentage">20% sobre salário</p>
                    <h4 class="expense-value">R$ ${(salario * 0.20).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
            </div>
            
            <div class="expenses-grid">
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-bus"></i>
                        <span>Vale Transporte</span>
                    </div>
                    <p class="expense-percentage">Benefício mensal</p>
                    <h4 class="expense-value">R$ ${valeTransporteBruto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-utensils"></i>
                        <span>Vale Alimentação</span>
                    </div>
                    <p class="expense-percentage">Benefício mensal</p>
                    <h4 class="expense-value">R$ ${valeAlimentacao.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h4>
                </div>
                
                <div class="expense-card">
                    <div class="stat-icon"><i class="fa-solid fa-calendar"></i></div>
                    <div class="expense-header">
                        <i class="fa-solid fa-calendar"></i>
                        <span>Dias Trabalhados</span>
                    </div>
                    <p class="expense-percentage">No mês</p>
                    <h4 class="expense-value">${diasTrabalhados} dias</h4>
                </div>
                
                <div class="expense-card">
                    <div class="expense-header">
                        <i class="fa-solid fa-clock"></i>
                        <span>Horas por Dia</span>
                    </div>
                    <p class="expense-percentage">Jornada diária</p>
                    <h4 class="expense-value">${func.horasTrabalhadasPorDia || 8}h</h4>
                </div>
            </div>
            
            <div class="total-card">
                <div class="total-content">
                    <i class="fa-solid fa-info-circle total-icon"></i>
                    <div>
                        <p class="total-label">Resumo do Pagamento</p>
                        <h2 class="total-value">Cargo: ${func.cargo}</h2>
                    </div>
                </div>
            </div>
            
            ${meusRelatorios.length > 0 ? `
            <div class="relatorios-lista">
                <h3><i class="fa-solid fa-history"></i> Histórico de Folhas Geradas</h3>
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
                            ${meusRelatorios.sort((a, b) => new Date(b.date) - new Date(a.date)).map(r => `
                                <tr>
                                    <td>${formatarData(r.date)}</td>
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
            </div>
            ` : '<div class="empty-state"><i class="fa-solid fa-inbox"></i><h3>Nenhuma folha gerada ainda</h3><p>Gere sua primeira folha para ver o histórico aqui.</p></div>'}`;
    } catch (error) {
        console.error('Erro:', error);
        c.innerHTML = `<div class="error-message"><i class="fa-solid fa-exclamation-triangle"></i> ${error.message}</div>`;
    }
}

function formatarData(data) {
    if (!data) return '-';
    const d = new Date(data);
    const meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
    return `${meses[d.getMonth()]}/${d.getFullYear()}`;
}

gerar();
