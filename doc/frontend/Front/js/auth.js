document.addEventListener('DOMContentLoaded', () => {
	const form = document.getElementById('loginForm');
	const errorEl = document.getElementById('error-msg');
	if (!form) return;
	form.addEventListener('submit', async (ev) => {
		ev.preventDefault();
		const username = document.getElementById('username').value.trim();
		const password = document.getElementById('password').value.trim();
		try {
			const res = await fetch('http://localhost:8080/auth/login', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ username, password })
			});
			if (!res.ok) {
				const data = await res.json().catch(() => ({}));
				errorEl.textContent = data.error || 'Usuário ou senha incorretos!';
				return;
			}
			const data = await res.json();
			sessionStorage.setItem('token', data.token);
			sessionStorage.setItem('role', data.role);
			sessionStorage.setItem('username', data.username);
			if (data.funcionarioId) {
				sessionStorage.setItem('funcionarioId', String(data.funcionarioId));
			}
			if (data.role === 'ADMIN') {
				window.location.href = 'index.html';
			} else {
				window.location.href = 'dashboard-funcionario.html';
			}
		} catch (err) {
			errorEl.textContent = 'Erro ao conectar ao servidor.';
		}
	});
});