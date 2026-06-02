@echo off
REM Script de setup e compilação para Windows
REM Instala dependências e compila o projeto

echo ======================================================
echo   SETUP - Cadastro de Fornecedores Automático
echo ======================================================
echo.

REM Verificar se Maven está instalado
echo [1/3] Verificando Maven...
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo ❌ Maven não está instalado ou não está no PATH!
    echo.
    echo Para instalar Maven:
    echo   1. Baixe em: https://maven.apache.org/download.cgi
    echo   2. Descompacte em um diretório (ex: C:\maven)
    echo   3. Adicione ao PATH do Windows:
    echo      Variável MAVEN_HOME = C:\maven
    echo      Adicione C:\maven\bin ao PATH
    echo.
    pause
    exit /b 1
)
echo ✓ Maven encontrado

REM Verificar se Java está instalado
echo.
echo [2/3] Verificando Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo ❌ Java não está instalado ou não está no PATH!
    echo.
    echo Para instalar Java 17+:
    echo   1. Baixe em: https://www.oracle.com/java/technologies/javase-downloads.html
    echo   2. Instale o JDK (não apenas JRE)
    echo   3. Adicione JAVA_HOME ao PATH do Windows
    echo.
    pause
    exit /b 1
)
echo ✓ Java encontrado

REM Compilar projeto
echo.
echo [3/3] Compilando projeto com Maven...
cd "%~dp0"
mvn clean compile
if %errorlevel% neq 0 (
    echo.
    echo ❌ Erro ao compilar o projeto!
    pause
    exit /b 1
)

echo.
echo ✓ Compilação concluída com sucesso!
echo.
echo Próximos passos:
echo   1. Configure o PostgreSQL
echo   2. Execute o schema.sql
echo   3. Atualize as credenciais em Main.java
echo   4. Execute: run.bat
echo.
pause
