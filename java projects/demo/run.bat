@echo off
REM Script para executar a aplicação

echo ======================================================
echo   EXECUTANDO - Cadastro de Fornecedores Automático
echo ======================================================
echo.

cd "%~dp0"

REM Verificar se já foi compilado
if not exist "target\classes\com\example\Main.class" (
    echo ❌ Projeto não foi compilado ainda!
    echo Execute setup.bat primeiro
    pause
    exit /b 1
)

REM Executar a aplicação
echo Iniciando aplicação...
echo.

mvn exec:java -Dexec.mainClass="com.example.Main" -Dexec.cleanupDaemonThreads=false

echo.
echo Aplicação encerrada.
pause
