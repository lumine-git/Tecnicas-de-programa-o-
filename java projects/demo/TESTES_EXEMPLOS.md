# 🧪 GUIA DE TESTES E EXEMPLOS

## 📋 Índice
1. [Teste da API (curl)](#teste-da-api-curl)
2. [Teste do Banco de Dados](#teste-do-banco-de-dados)
3. [Teste da Aplicação](#teste-da-aplicação)
4. [CNPJs para Teste](#cnpjs-para-teste)
5. [Troubleshooting](#troubleshooting)

---

## 🌐 Teste da API (curl)

### 1. Testar conexão com BrasilAPI

```bash
# Windows PowerShell
Invoke-RestMethod -Uri "https://brasilapi.com.br/api/cnpj/v1/62823257012964"

# Linux/macOS com curl
curl "https://brasilapi.com.br/api/cnpj/v1/62823257012964"

# Com formatação JSON
curl -s "https://brasilapi.com.br/api/cnpj/v1/62823257012964" | jq .
```

### 2. Resposta esperada

```json
{
  "cnpj": "62823257012964",
  "razao_social": "BRASIL API CNPJ LTDA",
  "nome_fantasia": "Brasil API",
  "descricao": "Consulta de dados públicos de CNPJ",
  "natureza_juridica": {
    "codigo": "2062",
    "descricao": "Sociedade Empresária Limitada"
  },
  "logradouro": "Avenida Paulista",
  "numero": "1578",
  "complemento": "Conjunto 32",
  "bairro": "Bela Vista",
  "municipio": "São Paulo",
  "uf": "SP",
  "cep": "01310100",
  "cnae_fiscal": "6201500",
  "cnae_fiscal_descricao": "Desenvolvimento de softwares sob encomenda",
  "qsa": [
    {
      "nome_socio": "João da Silva",
      "cnpj_cpf_do_socio": "12345678901",
      "qualificacao_socio": "Sócio-Gerente",
      "data_entrada_sociedade": "2020-01-15"
    }
  ]
}
```

### 3. Testar CNPJ inválido

```bash
# Deve retornar 404
curl -i "https://brasilapi.com.br/api/cnpj/v1/00000000000000"

# Resposta esperada
HTTP/1.1 404 Not Found
```

### 4. Testar CNPJ com formato

```bash
# A API aceita com ou sem formatação
curl "https://brasilapi.com.br/api/cnpj/v1/62.823.257/0129-64"

# Também funciona
curl "https://brasilapi.com.br/api/cnpj/v1/62823257012964"
```

---

## 🗄️ Teste do Banco de Dados

### 1. Verificar conexão

```sql
-- Conectar ao banco
psql -U postgres -d fornecedores

-- Ver versão
SELECT version();

-- Ver bancos
\l

-- Ver tabelas
\dt

-- Sair
\q
```

### 2. Verificar estrutura

```sql
-- Descrever tabela empresas
\d empresas

-- Descrever tabela socios
\d socios

-- Ver índices
SELECT * FROM pg_indexes WHERE tablename IN ('empresas', 'socios');
```

### 3. Inserir dados de teste

```sql
-- Inserir empresa
INSERT INTO empresas (cnpj, razao_social, nome_fantasia, logradouro, numero, bairro, municipio, uf, cep, cnae_fiscal, cnae_fiscal_descricao)
VALUES ('62823257012964', 'BRASIL API CNPJ LTDA', 'Brasil API', 'Avenida Paulista', '1578', 'Bela Vista', 'São Paulo', 'SP', '01310100', '6201500', 'Desenvolvimento de softwares sob encomenda');

-- Inserir sócio
INSERT INTO socios (cnpj_empresa, nome_socio, cnpj_cpf_do_socio, qualificacao_socio)
VALUES ('62823257012964', 'João da Silva', '12345678901', 'Sócio-Gerente');

-- Verificar
SELECT * FROM empresas WHERE cnpj = '62823257012964';
SELECT * FROM socios WHERE cnpj_empresa = '62823257012964';
```

### 4. Limpar dados de teste

```sql
-- Deletar empresa (remove sócios automaticamente por CASCADE)
DELETE FROM empresas WHERE cnpj = '62823257012964';

-- Verificar
SELECT COUNT(*) FROM empresas;
SELECT COUNT(*) FROM socios;
```

---

## ▶️ Teste da Aplicação

### 1. Compilar

```bash
cd "C:\Users\seu-usuario\Documents\java projects\demo"

# Com Maven
mvn clean compile

# Ou
mvn clean package
```

### 2. Executar

```bash
# Com Maven
mvn exec:java -Dexec.mainClass="com.example.Main"

# Ou com script Windows
run.bat

# Ou direto (se já compilado)
java -cp "target/classes:target/dependency/*" com.example.Main
```

### 3. Testar funcionalidades

#### Teste 1: Buscar e cadastrar
```
Escolha: 1
CNPJ: 62823257012964
Confirmar: S
Resultado esperado: ✅ Empresa cadastrada com sucesso!
```

#### Teste 2: Buscar cadastrado
```
Escolha: 2
CNPJ: 62823257012964
Resultado esperado: ✅ Empresa encontrada! [dados exibidos]
```

#### Teste 3: CNPJ inválido
```
Escolha: 1
CNPJ: 12345
Resultado esperado: ❌ CNPJ inválido: CNPJ deve conter exatamente 14 dígitos
```

#### Teste 4: Duplicação
```
Escolha: 1
CNPJ: 62823257012964
Resultado esperado: ⚠️ Esta empresa já está cadastrada. Deseja atualizar? (S/N)
```

---

## 📊 CNPJs para Teste

### CNPJs Reais (Ativos)

| CNPJ | Empresa | Município | UF |
|------|---------|-----------|-----|
| `62823257012964` | BRASIL API CNPJ LTDA | São Paulo | SP |
| `11222333000181` | EMPRESA TESTE LTDA | Rio de Janeiro | RJ |

**Encontrar mais:** https://www.cnpj.org.br/

### CNPJs para Teste de Erro

```
00000000000000 - CNPJ invalido (retorna 404)
11111111111111 - CNPJ invalido (retorna 404)
```

---

## 🔍 Cenários de Teste

### Scenario 1: Happy Path
```
1. Executar aplicação
2. Escolher opção 1
3. Digitar CNPJ válido (62823257012964)
4. Confirmar cadastro
5. Sistema salva no banco
6. Mensagem: ✅ Empresa cadastrada com sucesso!
```

### Scenario 2: Duplicação
```
1. Executar aplicação
2. Escolher opção 1
3. Digitar CNPJ já cadastrado
4. Sistema alerta: ⚠️ Já está cadastrada
5. Escolher: Não atualizar
6. Sistema cancela operação
```

### Scenario 3: Erro de Conexão
```
1. PostgreSQL OFFLINE
2. Executar aplicação
3. Sistema tenta conectar
4. Mensagem: ❌ Não foi possível conectar ao banco de dados
5. Aplicação encerra com erro
```

### Scenario 4: API Offline
```
1. Sem conexão com internet
2. Executar aplicação
3. Escolher opção 1
4. Digitar CNPJ
5. Mensagem: ❌ Erro de comunicação com a API
```

### Scenario 5: CNPJ Inválido
```
1. Executar aplicação
2. Escolher opção 1
3. Digitar: 12345 (menos de 14 dígitos)
4. Mensagem: ❌ CNPJ inválido: CNPJ deve conter exatamente 14 dígitos
```

---

## 📈 Teste de Carga (Opcional)

### Inserir 100 empresas

```sql
-- Script para teste de carga
INSERT INTO empresas (cnpj, razao_social, nome_fantasia, municipio, uf, data_criacao)
SELECT 
    '628232570129' || LPAD(i::TEXT, 2, '0') as cnpj,
    'Empresa Teste ' || i as razao_social,
    'Empresa ' || i as nome_fantasia,
    'São Paulo' as municipio,
    'SP' as uf,
    CURRENT_TIMESTAMP
FROM generate_series(1, 100) as i;

-- Verificar
SELECT COUNT(*) FROM empresas;
```

---

## 🐛 Debug e Log

### 1. Adicionar logs na aplicação

Edite `Main.java` para ver logs detalhados:

```java
System.out.println("[DEBUG] CNPJ: " + cnpj);
System.out.println("[DEBUG] Conectando ao banco...");
System.out.println("[DEBUG] Empresa encontrada: " + empresa);
```

### 2. Ver logs do PostgreSQL

```sql
-- Verificar queries lentas
SELECT query, calls, mean_time FROM pg_stat_statements
ORDER BY mean_time DESC
LIMIT 10;
```

### 3. Monitor de conexões

```sql
-- Ver conexões ativas
SELECT datname, usename, state FROM pg_stat_activity;

-- Contar conexões
SELECT COUNT(*) FROM pg_stat_activity;
```

---

## ✅ Checklist de Teste

- [ ] Java 17+ instalado
- [ ] Maven instalado
- [ ] PostgreSQL rodando
- [ ] Banco "fornecedores" criado
- [ ] Tabelas criadas (schema.sql executado)
- [ ] Projeto compila sem erros
- [ ] Aplicação inicia
- [ ] Conexão com banco funciona
- [ ] API BrasilAPI está online
- [ ] CNPJ válido é encontrado
- [ ] CNPJ inválido gera erro apropriado
- [ ] Dados são salvos no banco
- [ ] Busca retorna dados corretos
- [ ] Sócios são associados corretamente

---

## 🆘 Troubleshooting

### Erro: "Conexão recusada"
```
❌ ERROR: Erro de conexão com o banco de dados: Connection refused

Solução:
1. PostgreSQL está rodando? (verificar serviço)
2. Porta 5432 está correta?
3. Credenciais estão corretas?
4. Banco "fornecedores" existe?
```

### Erro: "Tabela não existe"
```
❌ ERROR: Erro ao buscar empresa: relation "empresas" does not exist

Solução:
Execute o script SQL:
psql -U postgres -d fornecedores -f schema.sql
```

### Erro: "CNPJ não encontrado na API"
```
❌ Empresa não encontrada na API ou ocorreu um erro

Solução:
1. Verifique se CNPJ é válido
2. Verifique conexão com internet
3. Teste em https://brasilapi.com.br/api/cnpj/v1/62823257012964
```

---

## 📝 Relatório de Teste

Modelo para documentar testes:

```markdown
## Teste: [Nome do Teste]
**Data:** [Data]
**Ambiente:** Windows/Linux/macOS
**Versão Java:** [versão]

### Pré-requisitos
- [ ] Java 17+
- [ ] PostgreSQL
- [ ] Banco criado

### Passos
1. ...
2. ...
3. ...

### Resultado
- [ ] Passou
- [ ] Falhou

### Observações
...
```

---

## 🎯 Próximos Testes (Sugestões)

1. **Teste de Performance** - Medir tempo de consulta
2. **Teste de Concorrência** - Múltiplas requisições
3. **Teste de Stress** - Banco com 10.000+ registros
4. **Teste de Segurança** - SQL Injection, XSS
5. **Teste de Integração** - Componentes em conjunto

---

**Última atualização:** Junho 2026
