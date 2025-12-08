
@@ -1,6 +1,3 @@
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=20461449&assignment_repo_type=AssignmentRepo)


## LINK EXECUÇÃO DO CODIGO 
https://drive.google.com/file/d/1Zujsi4DdHO4p-LyTXnHYaY3fdD_i3eUJ/view?usp=sharing


## LINK DE COMO EXECUTAR O CODIGO 

https://drive.google.com/file/d/1e8_iRsZYy7BFHIZUyaaui6o3g9AOv_bO/view?usp=sharing

## Banco de dados (MySQL via Docker)


```powershell
docker compose up -d
```

Conectar no DBeaver :
- Host: `localhost`
- Porta: `3306`
- Database: `folha_pagamento`
- Usuário: `admin`
- Senha: `admin123`
- Propriedades : `allowPublicKeyRetrieval=true`

## Como Executar o Sistema

### Pré-requisitos
- Docker Desktop instalado e em execução
- Java 17 ou superior
- Node.js e npm instalados

### 1️⃣ Iniciar o Banco de Dados

```powershell
docker-compose up -d
```

Aguarde alguns segundos para o MySQL inicializar completamente.

### 2️⃣ Iniciar o Backend (Spring Boot)

```powershell
cd calculo-folha-pagamento-lab2-grupo5\folha_pagamento_spring
.\mvnw.cmd spring-boot:run
```

O backend estará disponível em: `http://localhost:8080`

### 3️⃣ Iniciar o Frontend

```powershell
cd calculo-folha-pagamento-lab2-grupo5\doc\frontend\Front
npx serve -p 5500
```

O frontend estará disponível em: `http://localhost:5500`

### 4️⃣ Acessar o Sistema

Abra o navegador e acesse: `http://localhost:5500/login.html`

**Credenciais de Teste:**

**Administrador:**
- Usuário: `admin`
- Senha: `1234`
- Acesso: Dashboard completo, gestão de funcionários, folha de pagamento e relatórios empresariais

**Funcionário:**
- Usuário: `marcelo`
- Senha: `brasil2025`
- Acesso: Dashboard pessoal, visualização da própria folha de pagamento e relatórios individuais

### 📝 Notas Importantes

- O sistema cria automaticamente um usuário para cada funcionário cadastrado
- Senha padrão dos novos funcionários: `brasil2025`
- Login do funcionário = Nome do funcionário cadastrado
- O banco de dados persiste os dados entre execuções do Docker


