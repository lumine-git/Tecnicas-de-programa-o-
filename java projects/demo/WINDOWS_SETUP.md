# 🪟 GUIA ESPECÍFICO PARA WINDOWS

## 🎯 Conteúdo

1. [Verificação Inicial](#verificação-inicial)
2. [Instalação de Java](#instalação-de-java)
3. [Instalação de Maven](#instalação-de-maven)
4. [Instalação de PostgreSQL](#instalação-de-postgresql)
5. [Variáveis de Ambiente](#variáveis-de-ambiente)
6. [Troubleshooting](#troubleshooting)

---

## ✅ Verificação Inicial

### 1. Abra PowerShell como Administrador

1. Pressione `Win + X`
2. Escolha "Windows PowerShell (Admin)" ou "Terminal (Admin)"
3. Execute:

```powershell
# Verificar Java
java -version

# Verificar Maven
mvn --version

# Verificar PostgreSQL
psql --version
```

**Se qualquer comando falhar, siga as instruções de instalação abaixo.**

---

## ☕ Instalação de Java

### Opção 1: Oracle JDK (Recomendado)

1. Acesse: https://www.oracle.com/java/technologies/downloads/
2. Selecione **Java 17 (LTS)** ou superior
3. Clique em **Windows x64 Installer**
4. Baixe e execute o instalador
5. **IMPORTANTE:** Deixe marcado "Add to PATH"
6. Clique em "Next" até finalizar

### Opção 2: OpenJDK (Gratuito)

1. Acesse: https://adoptium.net/
2. Selecione **Eclipse Temurin JDK 17**
3. Clique em **Windows x64 Installer**
4. Baixe e execute
5. Deixe marcado "Add to PATH"
6. Clique em "Install"

### Opção 3: Chocolatey (Automático)

```powershell
# Abra PowerShell como Admin

# Se não tiver Chocolatey, instale primeiro:
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
iwr https://community.chocolatey.org/install.ps1 -UseBasicParsing | iex

# Instale Java
choco install openjdk17

# Reinicie o PowerShell
```

### Verificar Instalação

```powershell
java -version

# Deve exibir algo como:
# openjdk version "17.0.2"
```

**Se não funcionar, vá para [Variáveis de Ambiente](#variáveis-de-ambiente).**

---

## 📦 Instalação de Maven

### Opção 1: Manual (Recomendado)

1. Acesse: https://maven.apache.org/download.cgi
2. Procure por **Binary zip archive**
3. Clique no link (ex: apache-maven-3.9.5-bin.zip)
4. Extraia o arquivo em um local permanente, por exemplo:
   ```
   C:\tools\apache-maven-3.9.5\
   ```
5. Vá para [Variáveis de Ambiente](#variáveis-de-ambiente)

### Opção 2: Chocolatey (Automático)

```powershell
# PowerShell como Admin
choco install maven

# Reinicie o PowerShell
```

### Verificar Instalação

```powershell
mvn --version

# Deve exibir algo como:
# Apache Maven 3.9.5
# Maven home: C:\tools\apache-maven-3.9.5
```

---

## 🗄️ Instalação de PostgreSQL

### 1. Baixar Instalador

1. Acesse: https://www.postgresql.org/download/windows/
2. Clique em **Download the installer**
3. Escolha a versão mais recente (ex: 15 ou 16)
4. Clique no arquivo para baixar

### 2. Executar Instalador

1. Execute o arquivo baixado
2. Clique em "Next"
3. Escolha o caminho de instalação (default: `C:\Program Files\PostgreSQL\16\`)
4. Clique em "Next"
5. **IMPORTANTE:** Anote a senha do usuário `postgres`
6. Porta: `5432` (default, não mude)
7. Clique em "Next"
8. Locale: `Portuguese, Brazil`
9. Clique em "Next"
10. Clique em "Install"
11. **IMPORTANTE:** Deixe marcado "Stack Builder" para ferramentas extras
12. Clique em "Finish"

### 3. Verificar Instalação

```powershell
# Abra PowerShell (normal, não precisa admin)
psql --version

# Ou conecte ao PostgreSQL
psql -U postgres -h localhost

# Dentro do psql:
SELECT version();
\q
```

---

## 🔧 Variáveis de Ambiente

Se `java`, `mvn` ou `psql` não funcionarem, adicione ao PATH:

### 1. Abra as Configurações de Ambiente

**Método A: Via PowerShell (Fácil)**

```powershell
# PowerShell como Admin
$path = [Environment]::GetEnvironmentVariable("Path", "Machine")
$path += ";C:\Program Files\Java\jdk-17.0.2\bin"
$path += ";C:\tools\apache-maven-3.9.5\bin"
$path += ";C:\Program Files\PostgreSQL\16\bin"
[Environment]::SetEnvironmentVariable("Path", $path, "Machine")

# Feche e abra novamente o PowerShell
```

**Método B: Via GUI (Manual)**

1. Pressione `Win + X` → "Sistema"
2. Clique em "Configurações avançadas do sistema"
3. Clique em "Variáveis de Ambiente" (botão na parte inferior)
4. Na seção "Variáveis do sistema", clique em "Novo"

Adicione estas variáveis:

| Nome | Valor | Descrição |
|------|-------|-----------|
| `JAVA_HOME` | `C:\Program Files\Java\jdk-17.0.2` | Caminho da JDK |
| `MAVEN_HOME` | `C:\tools\apache-maven-3.9.5` | Caminho do Maven |
| `POSTGRESQL_HOME` | `C:\Program Files\PostgreSQL\16` | Caminho do PostgreSQL |

5. Edite a variável `Path` e adicione:
   - `%JAVA_HOME%\bin`
   - `%MAVEN_HOME%\bin`
   - `%POSTGRESQL_HOME%\bin`

6. Clique em "OK" em todas as janelas
7. **Reinicie o PowerShell**

### 2. Verificar

```powershell
# Feche e abra novo PowerShell

java -version
mvn --version
psql --version
```

---

## 🗄️ Criar Banco de Dados

### 1. Conectar ao PostgreSQL

```powershell
# Abra PowerShell
psql -U postgres -h localhost

# Digite a senha quando solicitado
```

### 2. Criar Banco

```sql
CREATE DATABASE fornecedores;

-- Conectar ao novo banco
\c fornecedores

-- Sair
\q
```

### 3. Executar Script SQL

```powershell
# Navegue até a pasta do projeto
cd "C:\Users\[seu-usuario]\Documents\java projects\demo"

# Execute o script
psql -U postgres -d fornecedores -f "src/main/resources/schema.sql"

# Se funcionar, verá: CREATE TABLE (sem erros)
```

---

## 🚀 Compilar e Executar

### 1. Compilar

```powershell
# Navegue até a pasta do projeto
cd "C:\Users\[seu-usuario]\Documents\java projects\demo"

# Compile
mvn clean compile

# Ou use o script
.\setup.bat
```

### 2. Executar

```powershell
# Via Maven
mvn exec:java -Dexec.mainClass="com.example.Main"

# Ou use o script
.\run.bat
```

---

## 🐛 Troubleshooting Windows

### ❌ "Java not found" ou "java: The term 'java' is not recognized"

**Solução:**

```powershell
# 1. Verificar se Java está instalado
dir "C:\Program Files\Java\"

# 2. Adicionar ao PATH
$env:Path += ";C:\Program Files\Java\jdk-17.0.2\bin"

# 3. Testar
java -version

# 4. Se funcionar, adicionar permanentemente:
# Siga: Variáveis de Ambiente (acima)
```

### ❌ "Maven not found" ou "mvn: The term 'mvn' is not recognized"

**Solução:**

```powershell
# 1. Verificar se Maven está instalado
dir "C:\tools\"  # ou onde você extraiu

# 2. Adicionar ao PATH
$env:Path += ";C:\tools\apache-maven-3.9.5\bin"

# 3. Testar
mvn --version

# 4. Se funcionar, adicionar permanentemente
```

### ❌ "PostgreSQL connection refused"

**Solução:**

```powershell
# 1. Verificar se serviço está rodando
Get-Service postgresql*

# 2. Se "Stopped", inicie
Start-Service postgresql-x64-16

# 3. Teste
psql -U postgres -h localhost

# 4. Se "FATAL: Ident authentication failed"
# Use password: psql -U postgres -h localhost -W
```

### ❌ "Cannot find -lm" ou erro de compilação

**Solução:**

```powershell
# Limpe o cache Maven
mvn clean

# Reinstale dependências
mvn dependency:resolve

# Tente compilar novamente
mvn compile
```

### ❌ "BUILD FAILURE - Cannot find symbol"

**Possíveis causas:**

```powershell
# 1. Java não está no PATH
java -version

# 2. Versão errada
# Verificar se é Java 17+

# 3. Limpar e recompilar
mvn clean compile
```

---

## 📊 Verificação Rápida

Execute este script para verificar tudo:

```powershell
# Salve como: verificar.ps1

Write-Host "=== VERIFICAÇÃO DO AMBIENTE ==="
Write-Host ""

# Java
Write-Host "1. Java:"
try {
    $version = java -version 2>&1 | Select-Object -First 1
    Write-Host "✓ $version" -ForegroundColor Green
} catch {
    Write-Host "✗ Java não encontrado" -ForegroundColor Red
}

# Maven
Write-Host ""
Write-Host "2. Maven:"
try {
    $mvn = mvn --version 2>&1 | Select-Object -First 1
    Write-Host "✓ $mvn" -ForegroundColor Green
} catch {
    Write-Host "✗ Maven não encontrado" -ForegroundColor Red
}

# PostgreSQL
Write-Host ""
Write-Host "3. PostgreSQL:"
try {
    $psql = psql --version 2>&1 | Select-Object -First 1
    Write-Host "✓ $psql" -ForegroundColor Green
} catch {
    Write-Host "✗ PostgreSQL não encontrado" -ForegroundColor Red
}

# Executar
# PowerShell como Admin:
# Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
# .\verificar.ps1
```

---

## 💾 Scripts Windows Úteis

### Script 1: Iniciar PostgreSQL

Salve como `iniciar-postgresql.bat`:

```batch
@echo off
echo Iniciando PostgreSQL...
net start postgresql-x64-16
pause
```

### Script 2: Parar PostgreSQL

Salve como `parar-postgresql.bat`:

```batch
@echo off
echo Parando PostgreSQL...
net stop postgresql-x64-16
pause
```

### Script 3: Compilar e Executar

Salve como `build-run.bat`:

```batch
@echo off
cd /d "%~dp0"
echo Compilando...
mvn clean compile
echo.
echo Executando...
mvn exec:java -Dexec.mainClass="com.example.Main"
pause
```

---

## 📝 Notas Importantes

1. **Senhas:** Não compartilhe a senha do PostgreSQL
2. **Porta 5432:** Se estiver em uso, mude na instalação
3. **Path:** Após adicionar ao PATH, reinicie o PowerShell
4. **Admin:** Use "Admin" se precisar instalar serviços
5. **Firewall:** Se PostgreSQL não conecta, verifique firewall

---

## 🆘 Última Resort

Se nada funcionar:

```powershell
# 1. Remova tudo e reinstale
# Java: Desinstale e reinstale
# Maven: Delete a pasta e baixe novamente
# PostgreSQL: Uninstall via "Add/Remove Programs"

# 2. Reinicie o PC

# 3. Execute em order:
# - Instale Java
# - Instale Maven
# - Instale PostgreSQL
# - Configure variáveis de ambiente
# - Reinicie PowerShell
# - Teste cada comando

# 4. Se ainda não funcionar, procure no Google:
# "windows java path não reconhecido"
# "postgresql connection refused windows"
```

---

**Última atualização:** Junho 2026
**Teste em:** Windows 10/11
