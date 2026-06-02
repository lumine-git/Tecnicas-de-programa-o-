# 📋 Cadastro de Fornecedores Automático

Aplicação **Java SE** que consulta dados de empresas via **BrasilAPI** e armazena em **PostgreSQL** usando **JDBC Puro**.

## 🎯 Arquitetura do Projeto

```
src/main/java/com/example/
├── Main.java                    # Camada de Serviço/Main (entrada da aplicação)
├── model/
│   ├── Empresa.java             # POJO - Dados principais da empresa
│   └── Socio.java               # POJO - Dados dos sócios (QSA)
├── client/
│   └── BrasilAPIClient.java     # Camada Client HTTP (consumo da API)
└── dao/
    └── EmpresaDAO.java          # Camada DAO (persistência JDBC)
```

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Build:** Maven
- **API:** BrasilAPI CNPJ (https://brasilapi.com.br/api/cnpj/v1/)
- **HTTP:** HttpClient nativo (java.net.http)
- **JSON:** Google Gson 2.10.1
- **Banco de Dados:** PostgreSQL
- **Acesso ao BD:** JDBC Puro (sem Hibernate/JPA)

## 📦 Dependências (pom.xml)

```xml
<dependencies>
    <!-- Google Gson para parsing JSON -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>

    <!-- PostgreSQL JDBC Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.1</version>
    </dependency>
</dependencies>
```

## 🚀 Como Começar

### 1️⃣ Instalar e Configurar PostgreSQL

```bash
# Windows (usando Chocolatey)
choco install postgresql

# Linux (Ubuntu/Debian)
sudo apt-get install postgresql postgresql-contrib

# macOS
brew install postgresql
```

### 2️⃣ Criar o Banco de Dados

```bash
# Conectar ao PostgreSQL
psql -U postgres

# Criar banco
CREATE DATABASE fornecedores;

# Conectar ao banco
\c fornecedores

# Executar o script SQL
\i 'C:/Users/seu-usuario/Documents/java projects/demo/src/main/resources/schema.sql'
```

Ou diretamente via linha de comando:

```bash
psql -U postgres -d fornecedores -f schema.sql
```

### 3️⃣ Configurar Credenciais (Main.java)

Abra `src/main/java/com/example/Main.java` e atualize as configurações:

```java
private static final String DB_URL = "jdbc:postgresql://localhost:5432/fornecedores";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "sua-senha";
```

### 4️⃣ Compilar e Executar

```bash
# Instalar dependências
mvn clean install

# Executar a aplicação
mvn exec:java -Dexec.mainClass="com.example.Main"

# Ou via IDE (executar Main.java)
```

## 📝 Fluxo de Funcionamento

```
┌─────────────────────────┐
│   Usuário digita CNPJ   │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│  BrasilAPIClient        │
│  • Limpa CNPJ           │
│  • Faz requisição HTTP  │
│  • Valida resposta 200  │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│  Gson (Parsing JSON)    │
│  • Mapeia para POJO     │
│  • Empresa + List(Sócio)│
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│  Validação de Dados     │
│  • Se já existe no BD?  │
│  • Confirmar com user?  │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│  EmpresaDAO             │
│  • Abre conexão JDBC    │
│  • Insert em transação  │
│  • Fecha recursos       │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│  ✅ Dados Persistidos   │
│  • Tabela empresas      │
│  • Tabela socios        │
└─────────────────────────┘
```

## 📊 Modelo de Dados

### Tabela: `empresas`
```sql
CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255),
    bairro VARCHAR(100),
    municipio VARCHAR(100),
    uf VARCHAR(2),
    cep VARCHAR(8),
    cnae_fiscal VARCHAR(10),
    cnae_fiscal_descricao TEXT,
    data_criacao TIMESTAMP,
    data_atualizacao TIMESTAMP
);
```

### Tabela: `socios`
```sql
CREATE TABLE socios (
    id SERIAL PRIMARY KEY,
    cnpj_empresa VARCHAR(14) REFERENCES empresas(cnpj) ON DELETE CASCADE,
    nome_socio VARCHAR(255) NOT NULL,
    cnpj_cpf_do_socio VARCHAR(20),
    qualificacao_socio VARCHAR(100),
    data_criacao TIMESTAMP
);
```

## 🔑 Mapeamento JSON → POJO

A classe `Empresa` usa anotações `@SerializedName` do Gson para mapear campos com nomes diferentes:

```java
@SerializedName("razao_social")
private String razaoSocial;  // JSON: razao_social → Java: razaoSocial

@SerializedName("nome_fantasia")
private String nomeFantasia;  // JSON: nome_fantasia → Java: nomeFantasia

private List<Socio> qsa;  // Array de sócios
```

## 🛡️ Tratamento de Exceções

A aplicação trata:

✅ **CNPJ Inválido** - Valida 14 dígitos
✅ **Formatação** - Remove pontos, traços e caracteres especiais
✅ **API Offline** - Captura IOException
✅ **Banco Offline** - SQLException com mensagens amigáveis
✅ **Recursos Não Fechados** - Try-with-resources em todas as conexões JDBC
✅ **Duplicidade** - Validação antes de inserir

## 📋 Exemplo de Uso

1. Execute a aplicação
2. Escolha opção **1** - Buscar e cadastrar
3. Digite um CNPJ (ex: `62.823.257/0129-64` ou `62823257012964`)
4. Confirme o cadastro
5. Os dados serão salvos no PostgreSQL

## 🔍 Testando com CNPJ Real

Você pode usar o CNPJ do exemplo da API:

**CNPJ:** `62823257012964`
**Empresa:** BRASIL API CNPJ LTDA

```bash
# Teste direto na API
curl "https://brasilapi.com.br/api/cnpj/v1/62823257012964"
```

## 🐛 Troubleshooting

### "Conexão recusada ao banco de dados"
```
→ PostgreSQL não está rodando
→ Verificar credenciais (usuário/senha)
→ Verificar se porta 5432 está disponível
```

### "Tabelas não encontradas"
```
→ Execute o schema.sql
→ Verifique se está no banco correto (fornecedores)
```

### "CNPJ não encontrado na API"
```
→ Verifique se o CNPJ é válido
→ Verifique sua conexão com a internet
```

## 📚 Conceitos Implementados

### ✅ Separação de Responsabilidades
- **Modelo:** POJOs (Empresa, Socio)
- **Client:** Consumo de API isolado
- **DAO:** Persistência concentrada
- **Main:** Orquestração do fluxo

### ✅ Boas Práticas JDBC
- Try-with-resources para garantir fechamento
- Prepared Statements contra SQL injection
- Transações para consistência
- Tratamento adequado de SQLExceptions

### ✅ Gestão de Dados
- CNPJ sem formatação no banco
- Mapeamento de listas (QSA)
- Foreign Keys para integridade referencial
- Indices para melhor performance

## 🎓 Critérios de Avaliação

| Critério | Status |
|----------|--------|
| Tratamento de Exceções | ✅ Implementado |
| Try-with-Resources | ✅ Implementado |
| Mapeamento de Listas | ✅ Implementado |
| Limpeza de Dados | ✅ Implementado |
| Camadas bem definidas | ✅ Implementado |
| JDBC Puro (sem Hibernate) | ✅ Implementado |
| Consumo de API com HttpClient | ✅ Implementado |
| Persistência em PostgreSQL | ✅ Implementado |

## 📄 Arquivos do Projeto

```
demo/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/
    │   │   ├── Main.java
    │   │   ├── model/
    │   │   │   ├── Empresa.java
    │   │   │   └── Socio.java
    │   │   ├── client/
    │   │   │   └── BrasilAPIClient.java
    │   │   └── dao/
    │   │       └── EmpresaDAO.java
    │   └── resources/
    │       └── schema.sql
    └── test/
        └── java/
```

## 🤝 Contribuições

Este é um projeto educacional. Sinta-se livre para estudar e modificar!

---

**Criado em:** Junho 2026
**Versão:** 1.0
