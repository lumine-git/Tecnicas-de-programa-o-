# 📚 ÍNDICE PRINCIPAL - COMECE AQUI!

## 🎯 Bem-vindo ao Projeto!

Esta é uma aplicação **Java SE** completa para cadastro automático de fornecedores usando a API BrasilAPI e PostgreSQL.

---

## 📖 Documentação Disponível

### 🚀 **Para começar rapidamente:**

| Arquivo | Para quem? | Leitura |
|---------|-----------|---------|
| **[README.md](README.md)** | Todos | ⭐⭐ (5 min) |
| **[WINDOWS_SETUP.md](WINDOWS_SETUP.md)** | Usuários Windows | ⭐⭐⭐ (10 min) |
| **[GUIA_INSTALACAO.md](GUIA_INSTALACAO.md)** | Setup detalhado | ⭐⭐⭐ (20 min) |

### 📚 **Para entender melhor:**

| Arquivo | Conteúdo |
|---------|----------|
| **[VISAO_GERAL.md](VISAO_GERAL.md)** | Arquitetura, componentes, conceitos |
| **[TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md)** | Como testar, CNPJs para teste, troubleshooting |

### 🔧 **Utilitários:**

| Arquivo | Função |
|---------|---------|
| **[app.properties.example](app.properties.example)** | Configurações da aplicação |
| **[QUERIES_UTEIS.sql](QUERIES_UTEIS.sql)** | Exemplos de SQL para o PostgreSQL |
| **[setup.bat](setup.bat)** | Script Windows para compilar |
| **[run.bat](run.bat)** | Script Windows para executar |

---

## 🗂️ Estrutura de Pastas

```
demo/
├── 📚 Documentação
│   ├── README.md                 ← Comece aqui!
│   ├── INDICE.md               ← Você está aqui
│   ├── WINDOWS_SETUP.md        ← Se está no Windows
│   ├── GUIA_INSTALACAO.md      ← Setup passo a passo
│   ├── VISAO_GERAL.md          ← Arquitetura
│   ├── TESTES_EXEMPLOS.md      ← Testes
│   └── QUERIES_UTEIS.sql       ← SQL útil
│
├── 🔧 Configuração
│   ├── pom.xml                 ← Dependências Maven
│   ├── setup.bat               ← Compilar (Windows)
│   └── run.bat                 ← Executar (Windows)
│
└── 💻 Código-fonte
    └── src/main/java/com/example/
        ├── Main.java            ← Entrada da aplicação
        ├── model/               ← POJOs
        │   ├── Empresa.java
        │   └── Socio.java
        ├── client/              ← Client HTTP
        │   └── BrasilAPIClient.java
        └── dao/                 ← Persistência
            └── EmpresaDAO.java
```

---

## ✅ Checklist de Setup

### Passo 1: Verificar Pré-requisitos
- [ ] Java 17+ instalado → `java -version`
- [ ] Maven instalado → `mvn --version`
- [ ] PostgreSQL instalado → `psql --version`

**Não tem? Siga [WINDOWS_SETUP.md](WINDOWS_SETUP.md) ou [GUIA_INSTALACAO.md](GUIA_INSTALACAO.md)**

### Passo 2: Configurar Banco de Dados
- [ ] Banco "fornecedores" criado
- [ ] Tabelas criadas (execute schema.sql)
- [ ] Conexão testada

**Como fazer? Veja [GUIA_INSTALACAO.md - Configuração do Banco de Dados](GUIA_INSTALACAO.md#configuração-do-banco-de-dados)**

### Passo 3: Compilar e Executar
- [ ] Projeto compilado com sucesso
- [ ] Aplicação executada
- [ ] Menu aparece corretamente

**Como fazer?**
```bash
# Compilar
mvn clean compile

# Executar
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### Passo 4: Testar
- [ ] Buscar CNPJ válido: `62823257012964`
- [ ] Dados aparecem corretamente
- [ ] Empresa é salva no banco
- [ ] Busca retorna dados

**Exemplos? Veja [TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md)**

---

## 🎓 Conceitos Implementados

✅ Arquitetura em camadas (Model, Client, DAO, Service)
✅ JDBC Puro (sem ORM)
✅ Try-with-resources (gestão de recursos)
✅ Transações ACID
✅ HttpClient nativo
✅ JSON com Gson
✅ Mapeamento de listas
✅ Exception handling
✅ PreparedStatements
✅ Foreign Keys

---

## 🚀 Início Rápido

### Opção 1: Windows (Automático)

```powershell
# 1. Compilar
.\setup.bat

# 2. Executar
.\run.bat

# 3. Use a aplicação
```

### Opção 2: Linha de Comando

```bash
# 1. Compilar
mvn clean compile

# 2. Executar
mvn exec:java -Dexec.mainClass="com.example.Main"

# 3. Use a aplicação
```

### Opção 3: IDE (Eclipse, IntelliJ)

1. Abra o projeto
2. Clique direito em `Main.java`
3. Selecione "Run as → Java Application"

---

## 📋 Fluxo Principal

```
┌─────────────────────────┐
│ Usuário inicia app      │
└──────────┬──────────────┘
           │
           ▼
┌─────────────────────────┐
│ Menu de Opções          │
│ 1. Buscar e cadastrar   │
│ 2. Buscar cadastrado    │
│ 3. Sair                 │
└──────────┬──────────────┘
           │
      ┌────┴────┬─────────┐
      │          │         │
      ▼1         ▼2        ▼3
   API CALL   QUERY BD   EXIT
      │          │         │
      ▼          ▼         ▼
   PARSE    DISPLAY    DONE
      │          │
      ▼          ▼
   SAVE      RETURN
      │
      ▼
   ✅ DONE
```

---

## 🐛 Se Algo Não Funcionar

### Problema 1: "Connection refused"
**Causa:** PostgreSQL não está rodando
**Solução:** Inicie PostgreSQL e verifique porta 5432

### Problema 2: "Table not found"
**Causa:** schema.sql não foi executado
**Solução:** Execute: `psql -U postgres -d fornecedores -f schema.sql`

### Problema 3: "Java not found"
**Causa:** Java não está no PATH
**Solução:** Veja [WINDOWS_SETUP.md](WINDOWS_SETUP.md#variáveis-de-ambiente)

### Problema 4: "Maven not found"
**Causa:** Maven não está instalado ou no PATH
**Solução:** Veja [GUIA_INSTALACAO.md](GUIA_INSTALACAO.md#instalação-do-maven)

**Mais problemas?** Veja [TESTES_EXEMPLOS.md - Troubleshooting](TESTES_EXEMPLOS.md#troubleshooting)

---

## 📞 Próximos Passos

### Após instalação bem-sucedida:

1. **Explorar o código**
   - Leia [VISAO_GERAL.md](VISAO_GERAL.md) para entender a arquitetura
   - Estude os comentários no código

2. **Testar com dados reais**
   - Use CNPJs da [TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md#cnpjs-para-teste)
   - Consulte o banco com [QUERIES_UTEIS.sql](QUERIES_UTEIS.sql)

3. **Estender o projeto**
   - Adicione validações
   - Implemente testes unitários
   - Crie API REST com Spring Boot
   - Adicione logging com Log4j

---

## 📚 Documentação Rápida

### Java
- Oficial: https://docs.oracle.com/en/java/javase/17/
- HttpClient: https://openjdk.java.net/groups/net/httpclient/

### PostgreSQL
- Oficial: https://www.postgresql.org/docs/
- Tipos de dados: https://www.postgresql.org/docs/current/datatype.html

### Maven
- Oficial: https://maven.apache.org/
- Repositório: https://mvnrepository.com/

### Gson
- GitHub: https://github.com/google/gson
- Documentação: https://www.javadoc.io/doc/com.google.code.gson/gson/

### BrasilAPI
- Site: https://brasilapi.com.br/
- CNPJ endpoint: https://brasilapi.com.br/api/cnpj/v1/

---

## 🎯 Roteiro de Leitura Recomendado

### Para usuários Windows:
1. ✅ Este arquivo (você está aqui!)
2. 📖 [WINDOWS_SETUP.md](WINDOWS_SETUP.md)
3. 📖 [README.md](README.md)
4. 🚀 Execute `setup.bat` e `run.bat`

### Para entender a arquitetura:
1. 📖 [VISAO_GERAL.md](VISAO_GERAL.md)
2. 📖 Leia o código em `src/main/java/`
3. 📖 [TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md)

### Para testar:
1. 📖 [TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md)
2. 🧪 Execute os testes propostos
3. 📊 Consulte [QUERIES_UTEIS.sql](QUERIES_UTEIS.sql)

---

## 💡 Dicas Úteis

- 📌 **Salve a senha do PostgreSQL** em um local seguro
- 📌 **Use CNPJ real** para testes: `62823257012964`
- 📌 **Verifique conexão** antes de usar: Teste PostgreSQL, Java e Internet
- 📌 **Leia os logs** se algo der errado
- 📌 **Reinicie** após adicionar variáveis de ambiente

---

## 📞 Suporte

Se tiver dúvidas:
1. Consulte [GUIA_INSTALACAO.md](GUIA_INSTALACAO.md) - Solução de Problemas
2. Veja [TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md) - Troubleshooting
3. Procure em [WINDOWS_SETUP.md](WINDOWS_SETUP.md) se está no Windows
4. Consulte documentação oficial dos componentes

---

## ✨ Resumo Executivo

| Item | Descrição |
|------|-----------|
| **Linguagem** | Java SE 17+ |
| **Framework** | Nenhum (Java puro) |
| **API** | BrasilAPI CNPJ |
| **Banco** | PostgreSQL |
| **Acesso BD** | JDBC Puro |
| **JSON** | Google Gson |
| **Build** | Apache Maven |
| **Arquitetura** | Camadas (Model/Client/DAO/Service) |

---

## 🎉 Você está pronto!

Escolha seu próximo passo:

- 👉 **Windows?** → Leia [WINDOWS_SETUP.md](WINDOWS_SETUP.md)
- 👉 **Setup?** → Leia [GUIA_INSTALACAO.md](GUIA_INSTALACAO.md)
- 👉 **Começar?** → Execute `setup.bat` e `run.bat`
- 👉 **Arquitetura?** → Leia [VISAO_GERAL.md](VISAO_GERAL.md)
- 👉 **Testar?** → Leia [TESTES_EXEMPLOS.md](TESTES_EXEMPLOS.md)

---

**Versão:** 1.0  
**Última atualização:** Junho 2026  
**Status:** ✅ Pronto para usar!
