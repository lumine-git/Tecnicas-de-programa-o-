-- ================================================================
-- QUERIES ÚTEIS PARA GERENCIAMENTO DO BANCO DE DADOS
-- ================================================================

-- ================================================================
-- 📊 CONSULTAS BÁSICAS
-- ================================================================

-- 1. Listar todas as empresas cadastradas
SELECT 
    cnpj, 
    razao_social, 
    nome_fantasia, 
    municipio, 
    uf,
    data_criacao
FROM empresas
ORDER BY data_criacao DESC;

-- 2. Listar todas as empresas com contagem de sócios
SELECT 
    e.cnpj,
    e.razao_social,
    e.nome_fantasia,
    COUNT(s.id) AS quantidade_socios,
    e.municipio,
    e.uf
FROM empresas e
LEFT JOIN socios s ON e.cnpj = s.cnpj_empresa
GROUP BY e.id, e.cnpj, e.razao_social, e.nome_fantasia, e.municipio, e.uf
ORDER BY e.razao_social;

-- 3. Buscar empresa específica por CNPJ
SELECT * FROM empresas WHERE cnpj = '62823257012964';

-- 4. Buscar sócios de uma empresa
SELECT 
    nome_socio,
    cnpj_cpf_do_socio,
    qualificacao_socio
FROM socios
WHERE cnpj_empresa = '62823257012964'
ORDER BY nome_socio;

-- ================================================================
-- 🔍 FILTROS E BUSCAS
-- ================================================================

-- 5. Buscar empresas por município
SELECT cnpj, razao_social, nome_fantasia, municipio
FROM empresas
WHERE LOWER(municipio) LIKE '%são paulo%'
ORDER BY razao_social;

-- 6. Buscar empresas por UF
SELECT cnpj, razao_social, municipio, uf
FROM empresas
WHERE uf = 'SP'
ORDER BY municipio, razao_social;

-- 7. Buscar empresas por razão social (parcial)
SELECT cnpj, razao_social, nome_fantasia, municipio
FROM empresas
WHERE LOWER(razao_social) LIKE '%ltda%'
ORDER BY razao_social;

-- 8. Buscar sócios por nome
SELECT 
    s.nome_socio,
    s.cnpj_cpf_do_socio,
    s.qualificacao_socio,
    e.razao_social AS empresa
FROM socios s
JOIN empresas e ON s.cnpj_empresa = e.cnpj
WHERE LOWER(s.nome_socio) LIKE '%joão%'
ORDER BY e.razao_social;

-- ================================================================
-- 📈 ESTATÍSTICAS E RELATÓRIOS
-- ================================================================

-- 9. Total de empresas cadastradas
SELECT COUNT(*) AS total_empresas FROM empresas;

-- 10. Total de sócios cadastrados
SELECT COUNT(*) AS total_socios FROM socios;

-- 11. Empresas por UF (relatório)
SELECT 
    uf,
    COUNT(*) AS quantidade,
    COUNT(DISTINCT municipio) AS cidades
FROM empresas
GROUP BY uf
ORDER BY quantidade DESC;

-- 12. Empresas por município (Top 10)
SELECT 
    municipio,
    COUNT(*) AS quantidade
FROM empresas
GROUP BY municipio
ORDER BY quantidade DESC
LIMIT 10;

-- 13. Qualificações de sócios (distribuição)
SELECT 
    qualificacao_socio,
    COUNT(*) AS quantidade
FROM socios
GROUP BY qualificacao_socio
ORDER BY quantidade DESC;

-- 14. Empresas com mais sócios
SELECT 
    e.cnpj,
    e.razao_social,
    COUNT(s.id) AS quantidade_socios
FROM empresas e
JOIN socios s ON e.cnpj = s.cnpj_empresa
GROUP BY e.id, e.cnpj, e.razao_social
ORDER BY quantidade_socios DESC
LIMIT 10;

-- 15. Empresas sem sócios cadastrados
SELECT 
    cnpj,
    razao_social,
    municipio,
    uf
FROM empresas
WHERE cnpj NOT IN (SELECT DISTINCT cnpj_empresa FROM socios)
ORDER BY razao_social;

-- ================================================================
-- 🗑️ LIMPEZA E MANUTENÇÃO
-- ================================================================

-- 16. Deletar uma empresa específica (e seus sócios)
-- DELETE FROM empresas WHERE cnpj = '62823257012964';

-- 17. Deletar todos os sócios de uma empresa
-- DELETE FROM socios WHERE cnpj_empresa = '62823257012964';

-- 18. Deletar todas as empresas e sócios (CUIDADO!)
-- DELETE FROM socios;
-- DELETE FROM empresas;

-- 19. Resetar sequências de IDs
-- ALTER SEQUENCE empresas_id_seq RESTART WITH 1;
-- ALTER SEQUENCE socios_id_seq RESTART WITH 1;

-- ================================================================
-- 📝 ATUALIZAÇÃO DE DADOS
-- ================================================================

-- 20. Atualizar nome fantasia de uma empresa
-- UPDATE empresas 
-- SET nome_fantasia = 'Novo Nome'
-- WHERE cnpj = '62823257012964';

-- 21. Atualizar múltiplos campos
-- UPDATE empresas
-- SET bairro = 'Novo Bairro', municipio = 'Nova Cidade', uf = 'MG'
-- WHERE cnpj = '62823257012964';

-- 22. Marcar data de atualização como hoje
-- UPDATE empresas
-- SET data_atualizacao = CURRENT_TIMESTAMP
-- WHERE cnpj = '62823257012964';

-- ================================================================
-- 🔗 JOINS E CONSULTAS COMPLEXAS
-- ================================================================

-- 23. Relatório completo: Empresa + Sócios
SELECT 
    e.cnpj,
    e.razao_social,
    e.nome_fantasia,
    e.logradouro,
    e.numero,
    e.bairro,
    e.municipio,
    e.uf,
    e.cep,
    s.nome_socio,
    s.cnpj_cpf_do_socio,
    s.qualificacao_socio
FROM empresas e
LEFT JOIN socios s ON e.cnpj = s.cnpj_empresa
ORDER BY e.razao_social, s.nome_socio;

-- 24. Sócios com múltiplas empresas
SELECT 
    s.cnpj_cpf_do_socio,
    s.nome_socio,
    COUNT(DISTINCT s.cnpj_empresa) AS numero_empresas
FROM socios s
GROUP BY s.cnpj_cpf_do_socio, s.nome_socio
HAVING COUNT(DISTINCT s.cnpj_empresa) > 1
ORDER BY numero_empresas DESC;

-- 25. Empresas no mesmo endereço
SELECT 
    logradouro,
    numero,
    bairro,
    municipio,
    COUNT(*) AS quantidade,
    STRING_AGG(razao_social, ', ') AS empresas
FROM empresas
WHERE logradouro IS NOT NULL
GROUP BY logradouro, numero, bairro, municipio
HAVING COUNT(*) > 1
ORDER BY municipio, logradouro;

-- ================================================================
-- 📅 ANÁLISE TEMPORAL
-- ================================================================

-- 26. Empresas cadastradas por data
SELECT 
    DATE(data_criacao) AS data,
    COUNT(*) AS quantidade
FROM empresas
GROUP BY DATE(data_criacao)
ORDER BY data DESC;

-- 27. Empresas cadastradas no último mês
SELECT 
    cnpj,
    razao_social,
    data_criacao
FROM empresas
WHERE data_criacao >= CURRENT_DATE - INTERVAL '30 days'
ORDER BY data_criacao DESC;

-- 28. Empresas não atualizadas há mais de 30 dias
SELECT 
    cnpj,
    razao_social,
    data_atualizacao,
    CURRENT_TIMESTAMP - data_atualizacao AS dias_sem_atualizacao
FROM empresas
WHERE data_atualizacao < CURRENT_TIMESTAMP - INTERVAL '30 days'
ORDER BY data_atualizacao;

-- ================================================================
-- 🔐 ÍNDICES E PERFORMANCE
-- ================================================================

-- 29. Listar todos os índices
SELECT * FROM pg_indexes WHERE tablename IN ('empresas', 'socios');

-- 30. Analisar performance de uma query
EXPLAIN ANALYZE
SELECT e.cnpj, e.razao_social, COUNT(s.id) AS socios
FROM empresas e
LEFT JOIN socios s ON e.cnpj = s.cnpj_empresa
GROUP BY e.id, e.cnpj, e.razao_social;

-- ================================================================
-- 📋 VIEWS ÚTEIS
-- ================================================================

-- Já criadas no schema.sql:
-- vw_fornecedores_resumo - Visão resumida de fornecedores

-- 31. Usar a view
SELECT * FROM vw_fornecedores_resumo
ORDER BY quantidade_socios DESC;

-- ================================================================
-- 🛠️ MANUTENÇÃO DO BANCO
-- ================================================================

-- 32. Vacuum (limpeza de espaço)
-- VACUUM;

-- 33. Analisar tabelas
-- ANALYZE empresas;
-- ANALYZE socios;

-- 34. Ver tamanho das tabelas
SELECT 
    tablename,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS size
FROM pg_tables
WHERE schemaname = 'public';

-- 35. Ver últimas queries executadas
-- SELECT query, calls, mean_time FROM pg_stat_statements
-- ORDER BY mean_time DESC
-- LIMIT 10;

-- ================================================================
-- 📌 NOTAS IMPORTANTES
-- ================================================================

-- 1. Use LOWER() para buscas case-insensitive
-- 2. Use % como wildcard em LIKE: '%termo%' encontra o termo em qualquer posição
-- 3. Use IS NULL / IS NOT NULL para campos nulos
-- 4. Use ORDER BY para ordenar resultados
-- 5. Use LIMIT para limitar resultados
-- 6. Use GROUP BY e HAVING para agregações
-- 7. Use JOIN para relacionar tabelas
-- 8. Sempre testar em desenvolvimento antes de executar em produção

-- ================================================================
-- 🔍 EXEMPLO: Busca Completa por CNPJ
-- ================================================================

/*
-- Para buscar todos os dados de uma empresa e seus sócios:
SELECT 
    e.cnpj,
    e.razao_social,
    e.nome_fantasia,
    e.logradouro,
    e.numero,
    e.complemento,
    e.bairro,
    e.municipio,
    e.uf,
    e.cep,
    e.cnae_fiscal,
    e.cnae_fiscal_descricao,
    s.nome_socio,
    s.cnpj_cpf_do_socio,
    s.qualificacao_socio
FROM empresas e
LEFT JOIN socios s ON e.cnpj = s.cnpj_empresa
WHERE e.cnpj = '62823257012964'
ORDER BY s.nome_socio;
*/

-- ================================================================
-- FIM DO ARQUIVO DE QUERIES
-- ================================================================
