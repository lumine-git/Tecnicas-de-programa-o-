package com.example.dao;

import com.example.model.Empresa;
import com.example.model.Socio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) responsável pela persistência de Empresas e Sócios no PostgreSQL.
 * Utiliza JDBC puro com try-with-resources para garantir fechamento de recursos.
 */
public class EmpresaDAO {

    private String url;
    private String usuario;
    private String senha;

    /**
     * Construtor que recebe as credenciais do banco de dados.
     *
     * @param url URL de conexão do banco (ex: jdbc:postgresql://localhost:5432/fornecedores)
     * @param usuario Nome do usuário PostgreSQL
     * @param senha Senha do usuário PostgreSQL
     */
    public EmpresaDAO(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Salva uma empresa e seus sócios no banco de dados.
     * Utiliza transação para garantir consistência.
     *
     * @param empresa objeto Empresa a ser salvo
     * @return true se salvo com sucesso, false caso contrário
     */
    public boolean salvarEmpresa(Empresa empresa) {
        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            // Desabilitar auto-commit para usar transação
            conn.setAutoCommit(false);

            try {
                // Inserir empresa
                String sqlEmpresa = "INSERT INTO empresas " +
                        "(cnpj, razao_social, nome_fantasia, logradouro, numero, complemento, " +
                        "bairro, municipio, uf, cep, cnae_fiscal, cnae_fiscal_descricao) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                        "ON CONFLICT (cnpj) DO UPDATE SET " +
                        "razao_social = EXCLUDED.razao_social, " +
                        "nome_fantasia = EXCLUDED.nome_fantasia, " +
                        "logradouro = EXCLUDED.logradouro";

                try (PreparedStatement pstmt = conn.prepareStatement(sqlEmpresa)) {
                    pstmt.setString(1, empresa.getCnpj());
                    pstmt.setString(2, empresa.getRazaoSocial());
                    pstmt.setString(3, empresa.getNomeFantasia());
                    pstmt.setString(4, empresa.getLogradouro());
                    pstmt.setString(5, empresa.getNumero());
                    pstmt.setString(6, empresa.getComplemento());
                    pstmt.setString(7, empresa.getBairro());
                    pstmt.setString(8, empresa.getMunicipio());
                    pstmt.setString(9, empresa.getUf());
                    pstmt.setString(10, empresa.getCep());
                    pstmt.setString(11, empresa.getCnaeFiscal());
                    pstmt.setString(12, empresa.getCnaeFiscalDescricao());

                    pstmt.executeUpdate();
                }

                // Limpar sócios antigos (opcional)
                String sqlDeleteSocios = "DELETE FROM socios WHERE cnpj_empresa = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlDeleteSocios)) {
                    pstmt.setString(1, empresa.getCnpj());
                    pstmt.executeUpdate();
                }

                // Inserir sócios
                if (empresa.getQsa() != null && !empresa.getQsa().isEmpty()) {
                    String sqlSocio = "INSERT INTO socios " +
                            "(cnpj_empresa, nome_socio, cnpj_cpf_do_socio, qualificacao_socio) " +
                            "VALUES (?, ?, ?, ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(sqlSocio)) {
                        for (Socio socio : empresa.getQsa()) {
                            pstmt.setString(1, empresa.getCnpj());
                            pstmt.setString(2, socio.getNomeSocio());
                            pstmt.setString(3, socio.getCnpjCpfDoSocio());
                            pstmt.setString(4, socio.getQualificacaoSocio());
                            pstmt.addBatch();
                        }
                        pstmt.executeBatch();
                    }
                }

                // Commit da transação
                conn.commit();
                System.out.println("Empresa " + empresa.getCnpj() + " salva com sucesso!");
                return true;

            } catch (SQLException e) {
                // Rollback em caso de erro
                conn.rollback();
                System.err.println("Erro ao salvar empresa. Transação revertida: " + e.getMessage());
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca uma empresa pelo CNPJ.
     *
     * @param cnpj CNPJ da empresa (sem formatação)
     * @return objeto Empresa ou null se não encontrado
     */
    public Empresa buscarEmpresa(String cnpj) {
        String sql = "SELECT * FROM empresas WHERE cnpj = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cnpj);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazaoSocial(rs.getString("razao_social"));
                empresa.setNomeFantasia(rs.getString("nome_fantasia"));
                empresa.setLogradouro(rs.getString("logradouro"));
                empresa.setNumero(rs.getString("numero"));
                empresa.setComplemento(rs.getString("complemento"));
                empresa.setBairro(rs.getString("bairro"));
                empresa.setMunicipio(rs.getString("municipio"));
                empresa.setUf(rs.getString("uf"));
                empresa.setCep(rs.getString("cep"));
                empresa.setCnaeFiscal(rs.getString("cnae_fiscal"));
                empresa.setCnaeFiscalDescricao(rs.getString("cnae_fiscal_descricao"));

                // Buscar sócios
                empresa.setQsa(buscarSociosPorCnpj(cnpj));

                return empresa;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar empresa: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Busca todos os sócios de uma empresa.
     *
     * @param cnpjEmpresa CNPJ da empresa
     * @return List de Socios
     */
    private List<Socio> buscarSociosPorCnpj(String cnpjEmpresa) {
        List<Socio> socios = new ArrayList<>();
        String sql = "SELECT * FROM socios WHERE cnpj_empresa = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cnpjEmpresa);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Socio socio = new Socio();
                socio.setNomeSocio(rs.getString("nome_socio"));
                socio.setCnpjCpfDoSocio(rs.getString("cnpj_cpf_do_socio"));
                socio.setQualificacaoSocio(rs.getString("qualificacao_socio"));
                socios.add(socio);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar sócios: " + e.getMessage());
            e.printStackTrace();
        }

        return socios;
    }

    /**
     * Verifica se uma empresa já está cadastrada.
     *
     * @param cnpj CNPJ da empresa
     * @return true se existe, false caso contrário
     */
    public boolean empresaExiste(String cnpj) {
        String sql = "SELECT COUNT(*) FROM empresas WHERE cnpj = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cnpj);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar existência da empresa: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Testa a conexão com o banco de dados.
     *
     * @return true se conseguiu conectar, false caso contrário
     */
    public boolean testarConexao() {
        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("✓ Conexão com banco de dados estabelecida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Erro ao conectar ao banco de dados: " + e.getMessage());
            return false;
        }
    }
}
