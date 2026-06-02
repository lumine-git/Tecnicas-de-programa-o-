# 🚀 GUIA COMPLETO DE INSTALAÇÃO E USO

## 📋 Índice
1. [Pré-requisitos](#pré-requisitos)
2. [Instalação do Java](#instalação-do-java)
3. [Instalação do Maven](#instalação-do-maven)
4. [Instalação do PostgreSQL](#instalação-do-postgresql)
5. [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
6. [Compilação do Projeto](#compilação-do-projeto)
7. [Execução da Aplicação](#execução-da-aplicação)
8. [Solução de Problemas](#solução-de-problemas)

---

## Pré-requisitos

Você precisará ter instalado no seu computador:
- ✅ Java Development Kit (JDK) 17 ou superior
- ✅ Maven 3.6 ou superior
- ✅ PostgreSQL 12 ou superior
- ✅ Git (opcional, para clonar repositórios)

---

## Instalação do Java

### Windows

**Opção 1: Oracle JDK (Recomendado)**
1. Acesse: https://www.oracle.com/java/technologies/javase-downloads.html
2. Baixe o **JDK 17** (ou versão mais recente)
3. Execute o instalador
4. **IMPORTANTE:** Marque "Add to PATH"

**Opção 2: OpenJDK (Gratuito)**
1. Acesse: https://adoptium.net/
2. Baixe o **Eclipse Temurin JDK 17**
3. Execute o instalador
4. Anote o caminho de instalação

**Verificar instalação:**
```powershell
java -version
javac -version
```

Se aparecer a versão do Java, está correto! Se não, adicione ao PATH:
1. Pressione `Win + X` → "Sistema"
2. Clique em "Configurações avançadas do sistema"
3. Clique em "Variáveis de Ambiente"
4. Adicione/Edite `JAVA_HOME` com o caminho da instalação
5. Adicione `%JAVA_HOME%\bin` ao PATH

### Linux (Ubuntu/Debian)

```bash
sudo apt-get update
sudo apt-get install openjdk-17-jdk-headless

# Verificar
java -version
```

### macOS

```bash
# Usando Homebrew
brew install java17

# Ou
brew install openjdk@17

# Adicionar ao ~/.zprofile ou ~/.bash_profile
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

---

## Instalação do Maven

### Windows

**Opção 1: Instalador automático (Recomendado)**
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe o **Binary zip archive**
3. Descompacte em: `C:\apache-maven-3.9.x`

**Opção 2: Chocolatey**
```powershell
choco install maven
```

**Configurar variáveis de ambiente:**
1. Pressione `Win + X` → "Sistema"
2. Clique em "Configurações avançadas do sistema"
3. Clique em "Variáveis de Ambiente"
4. Crie uma nova variável: `MAVEN_HOME` = `C:\apache-maven-3.9.x`
5. Adicione `%MAVEN_HOME%\bin` ao PATH

**Verificar instalação:**
```powershell
mvn --version
```

### Linux

```bash
# Ubuntu/Debian
sudo apt-get install maven

# Verificar
mvn --version
```

### macOS

```bash
# Homebrew
brew install maven

# Verificar
mvn --version
```

---

## Instalação do PostgreSQL

### Windows

1. Acesse: https://www.postgresql.org/download/windows/
2. Baixe o **Interactive installer**
3. Execute o instalador
4. **IMPORTANTE:** Anote a senha do usuário `postgres`
5. Porta padrão: 5432
6. Marque "Stack Builder" para ferramentas adicionais

**Verificar instalação:**
```powershell
# Abra o Command Prompt e teste
psql -U postgres -h localhost
```

### Linux (Ubuntu/Debian)

```bash
sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

# Inicie o serviço
sudo systemctl start postgresql
sudo systemctl enable postgresql

# Verifique
sudo -u postgres psql --version
```

### macOS

```bash
# Homebrew
brew install postgresql@15

# Inicie o serviço
brew services start postgresql@15

# Verifique
psql --version
```

---

## Configuração do Banco de Dados

### 1️⃣ Criar o banco de dados

**Windows Command Prompt:**
```cmd
psql -U postgres -h localhost
```

**Linux/macOS:**
```bash
sudo -u postgres psql
```

**Dentro do PostgreSQL:**
```sql
-- Criar banco de dados
CREATE DATABASE fornecedores;

-- Conectar ao banco
\c fornecedores
```

### 2️⃣ Criar as tabelas

Execute o script SQL:

**Opção A: Usando psql (Recomendado)**
```bash
psql -U postgres -d fornecedores -f "C:\Caminho\para\schema.sql"
```

**Opção B: Manualmente**
1. Abra o pgAdmin (ferramenta gráfica do PostgreSQL)
2. Conecte ao servidor PostgreSQL
3. Selecione o banco `fornecedores`
4. Clique em "Query Tool"
5. Copie todo o conteúdo de `schema.sql`
6. Cole na Query Tool e execute

**Opção C: Pela linha de comando**
```bash
psql -U postgres -d fornecedores
```

Dentro do psql, execute:
```sql
\i 'C:/Caminho/para/schema.sql'
```

### 3️⃣ Verificar se as tabelas foram criadas

```sql
\dt  -- Listar todas as tabelas
```

Você deve ver:
- `empresas`
- `socios`
- Índices
- Views

---

## Compilação do Projeto

### Opção 1: Script automático (Windows)

Simplesmente execute:
```powershell
setup.bat
```

### Opção 2: Manualmente

```bash
# Navegue até a pasta do projeto
cd "C:\Users\seu-usuario\Documents\java projects\demo"

# Instale as dependências e compile
mvn clean compile

# Se tudo der certo, verá:
# BUILD SUCCESS
```

**Se houver erros:**

```bash
# Limpe o cache e tente novamente
mvn clean

# Reinstale as dependências
mvn dependency:resolve

# Compile novamente
mvn compile
```

---

## Execução da Aplicação

### Opção 1: Script automático (Windows)

```powershell
run.bat
```

### Opção 2: Comando Maven

```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### Opção 3: IDE (Eclipse, IntelliJ)

1. Abra o projeto na sua IDE
2. Clique direito em `Main.java`
3. Selecione "Run as" → "Java Application"

---

## Usando a Aplicação

### 🎯 Interface

Quando você executar a aplicação, verá:

```
╔════════════════════════════════════════════════════════════╗
║     CADASTRO DE FORNECEDORES - BUSCA DE CNPJ              ║
║        Integração com BrasilAPI + PostgreSQL              ║
╚════════════════════════════════════════════════════════════╝

✓ Conexão com banco de dados estabelecida com sucesso!

📋 OPÇÕES:
1 - Buscar e cadastrar fornecedor por CNPJ
2 - Buscar fornecedor cadastrado
3 - Sair

Escolha uma opção:
```

### ✅ Opção 1: Buscar e Cadastrar

1. Digite **1** e pressione Enter
2. Digite um CNPJ (com ou sem formatação):
   - Formatos aceitos: `62823257012964` ou `62.823.257/0129-64`
3. A aplicação vai:
   - Consultar a API BrasilAPI
   - Validar se existe no banco
   - Solicitar confirmação
   - Salvar empresa e sócios

### ✅ Opção 2: Buscar Cadastrado

1. Digite **2** e pressione Enter
2. Digite o CNPJ de uma empresa já cadastrada
3. A aplicação mostrará todos os dados persistidos

### ✅ Opção 3: Sair

Digite **3** para encerrar a aplicação.

---

## CNPJ para Teste

**Empresa Real (usada nos exemplos):**
- **CNPJ:** `62.823.257/0129-64` ou `62823257012964`
- **Empresa:** BRASIL API CNPJ LTDA
- **Status:** Ativa

Você pode encontrar mais CNPJs válidos em: https://www.cnpj.org.br/

---

## Solução de Problemas

### ❌ "PostgreSQL Connection Refused"

**Causa:** PostgreSQL não está rodando ou credenciais incorretas

**Solução:**
```powershell
# Windows - Verificar se o serviço está rodando
Get-Service postgresql*

# Ou inicie manualmente
pg_ctl -D "C:\Program Files\PostgreSQL\15\data" start

# Linux
sudo systemctl start postgresql
```

Atualize em `Main.java`:
```java
private static final String DB_URL = "jdbc:postgresql://localhost:5432/fornecedores";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "sua-senha-aqui";
```

### ❌ "Table empresas does not exist"

**Causa:** O script SQL não foi executado

**Solução:**
```bash
# Execute o schema.sql
psql -U postgres -d fornecedores -f schema.sql

# Verifique
psql -U postgres -d fornecedores
\dt
```

### ❌ "CNPJ não encontrado na API"

**Causa:** O CNPJ não existe ou está inválido

**Solução:**
- Verifique se o CNPJ está correto
- Verifique sua conexão com a internet
- Teste em: https://brasilapi.com.br/api/cnpj/v1/62823257012964

### ❌ "Maven command not found"

**Causa:** Maven não está no PATH

**Solução:**
1. Reinstale Maven seguindo [este guia](#instalação-do-maven)
2. Reinicie o terminal/PowerShell após adicionar ao PATH

### ❌ "javac command not found"

**Causa:** Java não está no PATH

**Solução:**
1. Reinstale Java seguindo [este guia](#instalação-do-java)
2. Reinicie o terminal/PowerShell após adicionar ao PATH

---

## 📚 Conceitos Implementados

Este projeto demonstra:

✅ **Java SE 17+** - Sem frameworks
✅ **HttpClient nativo** - Requisições HTTP modernas
✅ **Gson** - Parsing de JSON
✅ **JDBC Puro** - Acesso ao banco sem ORM
✅ **PostgreSQL** - Banco de dados relacional
✅ **Arquitetura em camadas** - Model, Client, DAO, Service
✅ **Try-with-resources** - Gestão de recursos
✅ **Transações JDBC** - Consistência de dados
✅ **Mapeamento de Listas** - Array JSON para List Java

---

## 🎓 Estrutura de Arquivos

```
demo/
├── pom.xml                          # Dependências Maven
├── README.md                        # Este arquivo
├── GUIA_INSTALACAO.md              # Guia de instalação (este arquivo)
├── setup.bat                        # Script de compilação (Windows)
├── run.bat                          # Script de execução (Windows)
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── Main.java            # Entrada da aplicação
│   │   │   ├── model/
│   │   │   │   ├── Empresa.java     # POJO da Empresa
│   │   │   │   └── Socio.java       # POJO do Sócio
│   │   │   ├── client/
│   │   │   │   └── BrasilAPIClient.java  # Cliente HTTP
│   │   │   └── dao/
│   │   │       └── EmpresaDAO.java  # DAO com JDBC
│   │   └── resources/
│   │       └── schema.sql           # Script SQL
│   └── test/
│       └── java/
└── target/
    └── classes/  # Arquivos compilados
```

---

## 📞 Suporte

Se você tiver dúvidas:
1. Leia novamente a [Solução de Problemas](#solução-de-problemas)
2. Verifique os logs de erro
3. Consulte a documentação oficial:
   - Maven: https://maven.apache.org/
   - PostgreSQL: https://www.postgresql.org/docs/
   - Java: https://docs.oracle.com/en/java/javase/17/

---

**Última atualização:** Junho 2026
**Versão:** 1.0
