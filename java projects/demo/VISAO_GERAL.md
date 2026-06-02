# 📦 VISÃO GERAL DO PROJETO

## 🎯 Objetivo

Desenvolver uma aplicação **Java SE** que consulte dados de empresas fornecedoras via **API BrasilAPI** e armazene os dados de forma persistente em um banco de dados **PostgreSQL**, implementando boas práticas de engenharia de software como separação de responsabilidades, gestão de recursos e tratamento de exceções.

---

## ✅ O que foi Implementado

### 1. 🏗️ Arquitetura em Camadas

```
┌──────────────────────────────────────────┐
│         CAMADA DE APRESENTAÇÃO           │
│  Main.java - Interface com usuário       │
│  • Menu interativo                       │
│  • Entrada/saída de dados                │
└──────────────┬───────────────────────────┘
               │
┌──────────────▼───────────────────────────┐
│       CAMADA DE NEGÓCIO/SERVIÇO          │
│  • Orquestração de fluxo                 │
│  • Validações                            │
│  • Decisões de negócio                   │
└──────────────┬───────────────────────────┘
               │
       ┌───────┴────────┐
       │                │
┌──────▼────────┐  ┌────▼──────────────┐
│ CAMADA CLIENT │  │ CAMADA DE DAO     │
│ BrasilAPI     │  │ EmpresaDAO        │
│ • HTTP GET    │  │ • JDBC Puro       │
│ • Parsing JSON│  │ • Transações      │
│ • Exceptions  │  │ • Persistência    │
└──────┬────────┘  └────┬──────────────┘
       │                │
       └───────┬────────┘
               │
       ┌───────▼────────────────┐
       │  CAMADA DE MODELO      │
       │  POJOs (Empresa, Socio)│
       │  • Getters/Setters     │
       │  • @SerializedName     │
       └───────┬────────────────┘
               │
┌──────────────▼───────────────────────────┐
│    CAMADA DE PERSISTÊNCIA                │
│    PostgreSQL + JDBC                     │
│    • Tabela empresas                     │
│    • Tabela socios                       │
└──────────────────────────────────────────┘
```

### 2. 📁 Estrutura de Arquivos

```
demo/
├── 📄 pom.xml                          # Configuração Maven (dependências)
├── 📄 README.md                        # Documentação principal
├── 📄 GUIA_INSTALACAO.md              # Guia completo de setup
├── 📄 VISAO_GERAL.md                  # Este arquivo
├── 📄 QUERIES_UTEIS.sql               # Exemplos de SQL úteis
│
├── 🔧 setup.bat                        # Script para compilar (Windows)
├── ▶️ run.bat                          # Script para executar (Windows)
│
└── 📁 src/
    ├── main/
    │   ├── 📁 java/com/example/
    │   │   ├── 📄 Main.java                      # Entrada da aplicação
    │   │   │
    │   │   ├── 📁 model/
    │   │   │   ├── 📄 Empresa.java               # POJO da Empresa
    │   │   │   └── 📄 Socio.java                 # POJO do Sócio
    │   │   │
    │   │   ├── 📁 client/
    │   │   │   └── 📄 BrasilAPIClient.java       # Cliente HTTP
    │   │   │
    │   │   └── 📁 dao/
    │   │       └── 📄 EmpresaDAO.java            # DAO com JDBC
    │   │
    │   └── 📁 resources/
    │       └── 📄 schema.sql                     # Script SQL
    │
    └── test/
        └── 📁 java/                             # Testes unitários (vazio)
```

### 3. 🔑 Componentes Principais

#### A. POJOs (Model Layer)
- **Empresa.java** - Representa dados principais da empresa
  - Mapeamento automático de JSON → Java via Gson
  - Anotações `@SerializedName` para campos com nomes diferentes
  - List de Socios (composição)
  
- **Socio.java** - Representa sócios e administradores
  - Dados do QSA (Quadro de Sócios e Administradores)
  - Relação One-to-Many com Empresa

#### B. Client HTTP (Client Layer)
- **BrasilAPIClient.java** - Consumo da API
  - HttpClient nativo do Java 17+
  - Limpeza e validação do CNPJ (14 dígitos)
  - Tratamento de erros HTTP
  - Parsing JSON com Gson

#### C. Persistência (DAO Layer)
- **EmpresaDAO.java** - Acesso ao banco de dados
  - JDBC Puro (sem ORM/Hibernate)
  - Transações ACID
  - Try-with-resources para segurança
  - PreparedStatements contra SQL injection
  - Operações: INSERT, SELECT, verificação de existência

#### D. Lógica Principal (Service/Main)
- **Main.java** - Orquestração
  - Menu interativo
  - Entrada do usuário
  - Validações
  - Fluxo de controle

### 4. 🗄️ Banco de Dados (PostgreSQL)

**Tabela: empresas**
```sql
├── id (PK)
├── cnpj (UNIQUE, 14 dígitos)
├── razao_social
├── nome_fantasia
├── logradouro, numero, complemento
├── bairro, municipio, uf, cep
├── cnae_fiscal, cnae_fiscal_descricao
├── data_criacao, data_atualizacao
└── Índices: cnpj, razao_social, municipio
```

**Tabela: socios**
```sql
├── id (PK)
├── cnpj_empresa (FK → empresas)
├── nome_socio
├── cnpj_cpf_do_socio
├── qualificacao_socio
├── data_criacao
└── Índices: cnpj_empresa, nome_socio
```

---

## 🚀 Como Usar

### Pré-requisitos
- ✅ Java 17+
- ✅ Maven 3.6+
- ✅ PostgreSQL 12+

### Passo a Passo

```bash
# 1. Instalar dependências e compilar
mvn clean install

# 2. Criar banco de dados
psql -U postgres -d fornecedores -f src/main/resources/schema.sql

# 3. Executar a aplicação
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### Fluxo de Uso

1. **Buscar e Cadastrar**
   ```
   Digite CNPJ → API BrasilAPI → Parse JSON → Validar → Banco PostgreSQL
   ```

2. **Buscar Cadastrado**
   ```
   Digite CNPJ → Query ao banco → Exibir dados
   ```

---

## 🛡️ Boas Práticas Implementadas

### 1. ✅ Gestão de Recursos
- **Try-with-resources** em todas as conexões JDBC
- **Automatic resource management**
- **Cleanup garantido** de Connection, PreparedStatement, ResultSet

```java
try (Connection conn = DriverManager.getConnection(url, usuario, senha);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // código
}
```

### 2. ✅ Tratamento de Exceções
- **IOException** - Falhas de comunicação HTTP
- **SQLException** - Problemas com banco de dados
- **IllegalArgumentException** - CNPJ inválido
- **InterruptedException** - Requisição interrompida
- Mensagens amigáveis ao usuário

```java
catch (SQLException e) {
    System.err.println("Erro ao salvar: " + e.getMessage());
    conn.rollback(); // Reverter transação
}
```

### 3. ✅ Segurança
- **PreparedStatements** contra SQL injection
- **Validação de entrada** (CNPJ com 14 dígitos)
- **Transações ACID** para consistência
- **Foreign Keys** para integridade referencial

```java
pstmt.setString(1, cnpj);  // Parameterized query
pstmt.executeUpdate();
```

### 4. ✅ Mapeamento de Dados
- **Gson @SerializedName** para JSON → Java
- **Composição** (Empresa contém List<Socio>)
- **Encapsulamento** (private + getters/setters)

```java
@SerializedName("razao_social")
private String razaoSocial;
```

### 5. ✅ Separação de Responsabilidades
- **Model** - Dados
- **Client** - Comunicação HTTP
- **DAO** - Persistência
- **Main** - Orquestração

### 6. ✅ Tratamento de Dados
- **Limpeza de CNPJ** - Remove pontos, traços
- **Validação** - 14 dígitos obrigatórios
- **Normalização** - Só números no banco

```java
String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");
if (cnpjLimpo.length() != 14) {
    throw new IllegalArgumentException("CNPJ inválido");
}
```

---

## 📊 Tecnologias Utilizadas

| Componente | Tecnologia | Versão |
|-----------|-----------|---------|
| **Linguagem** | Java SE | 17+ |
| **Build** | Maven | 3.6+ |
| **API HTTP** | HttpClient (nativo) | Java 17+ |
| **JSON** | Google Gson | 2.10.1 |
| **Banco de Dados** | PostgreSQL | 12+ |
| **JDBC** | postgresql-jdbc | 42.7.1 |

---

## 🎓 Conceitos Educacionais

Este projeto demonstra:

1. **Arquitetura em Camadas** - Separação clara de responsabilidades
2. **Padrão DAO** - Data Access Object Pattern
3. **JDBC Puro** - Sem frameworks ORM
4. **API Consumption** - HttpClient moderno
5. **JSON Parsing** - Gson e @SerializedName
6. **Transações ACID** - Consistência de dados
7. **Exception Handling** - Tratamento robusto de erros
8. **Resource Management** - Try-with-resources
9. **SQL Injection Prevention** - PreparedStatements
10. **POJO Design** - Model Objects

---

## 📋 Checklist de Avaliação

- ✅ **Linguagem:** Java SE (sem frameworks)
- ✅ **Consumo de API:** HttpClient nativo
- ✅ **Parsing JSON:** Google Gson
- ✅ **Persistência:** JDBC Puro
- ✅ **Banco de Dados:** PostgreSQL
- ✅ **Tratamento de Exceções:** Completo
- ✅ **Gestão de Recursos:** Try-with-resources
- ✅ **Mapeamento de Listas:** QSA implementado
- ✅ **Limpeza de Dados:** CNPJ normalizado
- ✅ **Arquitetura:** Separação em camadas

---

## 🔗 APIs e Recursos

**BrasilAPI CNPJ:**
```
GET https://brasilapi.com.br/api/cnpj/v1/{cnpj}

Exemplo:
GET https://brasilapi.com.br/api/cnpj/v1/62823257012964
```

**Documentação:**
- [BrasilAPI](https://brasilapi.com.br/)
- [Java HttpClient](https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpClient.html)
- [Gson](https://github.com/google/gson)
- [PostgreSQL JDBC](https://jdbc.postgresql.org/)

---

## 📝 Próximos Passos (Sugestões)

Para estender este projeto:

1. **Testes Unitários** - JUnit 5 + Mockito
2. **API REST** - Spring Boot
3. **Logging** - Log4j ou SLF4J
4. **Validação** - Bean Validation
5. **Caching** - Redis
6. **Migrations** - Flyway
7. **Docker** - Containerização
8. **Métricas** - Prometheus + Grafana

---

## 🆘 Troubleshooting

Se encontrar problemas:

1. **Erro de compilação** - Verifique Java 17+
2. **Conexão ao banco** - PostgreSQL está rodando?
3. **Tabelas não encontradas** - Execute schema.sql
4. **CNPJ não encontrado** - Verifique conexão com internet
5. **Maven not found** - Adicione ao PATH

Veja `GUIA_INSTALACAO.md` para mais detalhes.

---

## 📚 Documentação

- 📄 **README.md** - Visão geral e quick start
- 📄 **GUIA_INSTALACAO.md** - Instalação passo a passo
- 📄 **VISAO_GERAL.md** - Este arquivo
- 📄 **QUERIES_UTEIS.sql** - Exemplos SQL

---

## 📞 Suporte

Para dúvidas sobre:
- **Java:** https://docs.oracle.com/en/java/javase/17/
- **PostgreSQL:** https://www.postgresql.org/docs/
- **Maven:** https://maven.apache.org/
- **Gson:** https://github.com/google/gson

---

**Versão:** 1.0  
**Data:** Junho 2026  
**Status:** ✅ Completo
