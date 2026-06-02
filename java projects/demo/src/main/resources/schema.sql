-- Script de criação das tabelas para Cadastro de Fornecedores
-- Database: fornecedores
-- Tabelas: empresas, socios

-- Criar banco de dados (execute como superuser se não existir)
-- CREATE DATABASE fornecedores;

-- Conectar ao banco 'fornecedores' antes de executar o resto do script
-- \c fornecedores

-- ===============================================
-- TABELA: EMPRESAS
-- ===============================================
-- Armazena os dados principais das empresas
DROP TABLE IF EXISTS socios CASCADE;
DROP TABLE IF EXISTS empresas CASCADE;

CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
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
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para melhor performance
CREATE INDEX idx_cnpj ON empresas(cnpj);
CREATE INDEX idx_razao_social ON empresas(razao_social);
CREATE INDEX idx_municipio ON empresas(municipio);

-- Comentários explicativos
COMMENT ON TABLE empresas IS 'Armazena dados das empresas cadastradas via BrasilAPI';
COMMENT ON COLUMN empresas.cnpj IS 'CNPJ da empresa (14 dígitos, chave única)';
COMMENT ON COLUMN empresas.razao_social IS 'Nome oficial da empresa';
COMMENT ON COLUMN empresas.cnae_fiscal IS 'Código de Atividade Econômica Fiscal';

-- ===============================================
-- TABELA: SOCIOS
-- ===============================================
-- Armazena os sócios e administradores (QSA - Quadro de Sócios e Administradores)
CREATE TABLE socios (
    id SERIAL PRIMARY KEY,
    cnpj_empresa VARCHAR(14) NOT NULL REFERENCES empresas(cnpj) ON DELETE CASCADE,
    nome_socio VARCHAR(255) NOT NULL,
    cnpj_cpf_do_socio VARCHAR(20),
    qualificacao_socio VARCHAR(100),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para melhor performance
CREATE INDEX idx_cnpj_empresa ON socios(cnpj_empresa);
CREATE INDEX idx_nome_socio ON socios(nome_socio);

-- Comentários explicativos
COMMENT ON TABLE socios IS 'Armazena sócios e administradores das empresas (QSA)';
COMMENT ON COLUMN socios.cnpj_empresa IS 'CNPJ da empresa (Foreign Key)';
COMMENT ON COLUMN socios.cnpj_cpf_do_socio IS 'CPF ou CNPJ do sócio/administrador';

-- ===============================================
-- VIEW: RESUMO DE FORNECEDORES
-- ===============================================
-- Útil para visualização rápida dos dados
CREATE OR REPLACE VIEW vw_fornecedores_resumo AS
SELECT 
    e.cnpj,
    e.razao_social,
    e.nome_fantasia,
    e.municipio,
    e.uf,
    COUNT(s.id) AS quantidade_socios,
    e.data_criacao,
    e.data_atualizacao
FROM empresas e
LEFT JOIN socios s ON e.cnpj = s.cnpj_empresa
GROUP BY e.id, e.cnpj, e.razao_social, e.nome_fantasia, e.municipio, e.uf, e.data_criacao, e.data_atualizacao;

COMMENT ON VIEW vw_fornecedores_resumo IS 'Visão resumida de fornecedores com contagem de sócios';

-- ===============================================
-- DADOS DE TESTE (OPCIONAL)
-- ===============================================
-- Descomente para inserir dados de teste

/*
INSERT INTO empresas (cnpj, razao_social, nome_fantasia, logradouro, numero, bairro, municipio, uf, cep, cnae_fiscal, cnae_fiscal_descricao)
VALUES 
('62823257012964', 'BRASIL API CNPJ LTDA', 'Brasil API', 'Rua Teste', '123', 'Centro', 'São Paulo', 'SP', '01310100', '6201500', 'Desenvolvimento de softwares sob encomenda');

INSERT INTO socios (cnpj_empresa, nome_socio, cnpj_cpf_do_socio, qualificacao_socio)
VALUES 
('62823257012964', 'João Silva', '12345678910', 'Sócio-Gerente');
*/

-- ===============================================
-- FIM DO SCRIPT
-- ===============================================
-- Execute os comandos acima no PostgreSQL para criar a estrutura necessária.
-- Depois, configure as credenciais no arquivo Main.java (DB_URL, DB_USER, DB_PASSWORD)
