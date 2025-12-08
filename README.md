***RF 6,7,8 - Calculo de Impostos***

**Para desenvolvermos os requisitos Calcular Desconto de INSS RF6,  Calcular FGTS RF7 e Calcular Desconto de IRRF RF8, desenvolvemos uma classe abstrata Imposto para definir um contrato de cálculo que é implementado de forma específica pelas classes INSS, IRRF e FGTS, permitindo o cálculo polimórfico de diferentes tributos sobre a folha de pagamento do funcionários, e relatórios.**

*A herança implementada permite que as subclasses (INSS, IRRF, FGTS) reutilizem a estrutura e o contrato definidos pela superclasse Imposto. Esta abordagem estabelece uma relação do tipo é um**(a classe INSS é um tipo de Imposto).

*A classe Imposto abstrai o conceito de um cálculo de imposto, definindo um método calcularImposto sem fornecer uma implementação. A abstração esconde a complexidade dos cálculos específicos dentro de cada subclasse, garantindo o encasuplamento do método. (Quem utiliza as classes não precisa conhecer os detalhes de cada cálculo; basta invocar o método padrão calcularImposto).* 

*Graças ao polimorfismo, classes diferentes como INSS, IRRF podem ser tratadas de maneira uniforme através da superclasse Imposto. Isso permite, processar uma lista de diferentes impostos e calcular seus valores em loop, sem a necessidade de verificar o tipo específico de cada um, apenas com o método calcularImposto, inclusive facilita o acoplamento de novos métodos de possiveis novas tributações.*

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


