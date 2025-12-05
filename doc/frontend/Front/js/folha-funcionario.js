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
const container = document.getElementById('folhaContainer');
const API_FUNC = 'http://localhost:8080/api/funcionarios';
const API_CALC = 'http://localhost:8080/api/calculos/folha';

document.getElementById('gerarFolha').addEventListener('click', async () => {
    container.innerHTML = '<div class="loading"><i class="fa-solid fa-spinner fa-spin"></i> Calculando sua folha de pagamento...</div>';
    
    try {
        const respFunc = await fetch(`${API_FUNC}/${funcionarioId}`);
        if (!respFunc.ok) throw new Error('Erro ao carregar seus dados');
        
        const func = await respFunc.json();
        
        const respCalc = await fetch(`${API_CALC}/${funcionarioId}`);
        if (!respCalc.ok) throw new Error('Erro ao calcular folha');
        
        const dados = await respCalc.json();
        
        const adicionalPericulosidade = dados.adicionalPericulosidade || 0;
        const adicionalInsalubridade = dados.adicionalInsalubridade || 0;
        const inss = dados.inss || 0;
        const irrf = dados.irrf || 0;
        const fgts = dados.fgts || 0;
        const valeTransporteDesconto = dados.valeTransporte || 0;
        
        const salarioBase = dados.salarioBruto || 1;
        const percINSS = ((inss / salarioBase) * 100).toFixed(2);
        const percIRRF = ((irrf / salarioBase) * 100).toFixed(2);
        
        const valeTransporteBrutoTotal = (func.valorValeTransporte || 0) * (func.diasTrabalhadosNoMes || 22);
        const seisPercent = salarioBase * 0.06;
        const percVT = valeTransporteBrutoTotal > seisPercent ? '6.00' : ((valeTransporteDesconto / salarioBase) * 100).toFixed(2);
        
        const valeTransporteBruto = (func.valorValeTransporte || 0) * (func.diasTrabalhadosNoMes || 22);
        const valeAlimentacaoBruto = (func.valorValeAlimentacaoDiario || 0) * (func.diasTrabalhadosNoMes || 22);
        
        const horasDia = func.horasTrabalhadasPorDia || 8;
        const diasMes = func.diasTrabalhadosNoMes || 22;
        const totalHorasMes = horasDia * diasMes;
        const salarioHora = salarioBase / totalHorasMes;
        
        container.innerHTML = `
        <div class="folha-card">
            <div class="folha-header">
                <div>
                    <h3><i class="fa-solid fa-user"></i> ${func.nome}</h3>
                    <p class="cargo">${func.cargo}</p>
                </div>
                <div class="salario-liquido">
                    <span class="label">Salário Líquido</span>
                    <span class="valor">R$ ${dados.salarioLiquidoEstimado.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                </div>
            </div>
            
            <div class="folha-detalhes">
                <div class="secao">
                    <h4><i class="fa-solid fa-plus-circle"></i> Proventos</h4>
                    <div class="item">
                        <span>Salário Base</span>
                        <span>R$ ${dados.salarioBruto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>
                    <div class="item">
                        <span>Salário/Hora</span>
                        <span>R$ ${salarioHora.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}/h</span>
                    </div>
                    ${adicionalPericulosidade > 0 ? `
                    <div class="item">
                        <span>Adicional Periculosidade (30%)</span>
                        <span>R$ ${adicionalPericulosidade.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>` : ''}
                    ${adicionalInsalubridade > 0 ? `
                    <div class="item">
                        <span>Adicional Insalubridade</span>
                        <span>R$ ${adicionalInsalubridade.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>` : ''}
                </div>
                
                <div class="secao descontos">
                    <h4><i class="fa-solid fa-minus-circle"></i> Descontos</h4>
                    <div class="item">
                        <span>INSS (${percINSS}%)</span>
                        <span class="negativo">- R$ ${inss.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>
                    <div class="item">
                        <span>IRRF (${percIRRF}%)</span>
                        <span class="negativo">- R$ ${irrf.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>
                    ${valeTransporteDesconto > 0 ? `
                    <div class="item">
                        <span>Vale Transporte (${percVT}%)</span>
                        <span class="negativo">- R$ ${valeTransporteDesconto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>` : ''}
                </div>
                
                <div class="secao beneficios">
                    <h4><i class="fa-solid fa-gift"></i> Benefícios</h4>
                    ${valeTransporteBruto > 0 ? `
                    <div class="item">
                        <span>Vale Transporte</span>
                        <span>R$ ${valeTransporteBruto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>` : ''}
                    ${valeAlimentacaoBruto > 0 ? `
                    <div class="item">
                        <span>Vale Alimentação</span>
                        <span>R$ ${valeAlimentacaoBruto.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>` : ''}
                    ${valeTransporteBruto === 0 && valeAlimentacaoBruto === 0 ? '<div class="item"><span>Nenhum benefício</span><span>-</span></div>' : ''}
                </div>
                
                <div class="secao empresa">
                    <h4><i class="fa-solid fa-building"></i> Encargos da Empresa</h4>
                    <div class="item">
                        <span>FGTS (8%)</span>
                        <span>R$ ${fgts.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>
                    <div class="item">
                        <span>INSS Patronal (20%)</span>
                        <span>R$ ${(dados.salarioBruto * 0.20).toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2})}</span>
                    </div>
                </div>
            </div>
        </div>`;
        
    } catch (error) {
        container.innerHTML = `<div class="error-message"><i class="fa-solid fa-exclamation-triangle"></i> ${error.message}</div>`;
    }
});
