package com.example;

import com.example.model.Empresa;
import com.example.client.BrasilAPIClient;
import com.example.dao.EmpresaDAO;
import java.util.Scanner;

/**
 * Classe principal que orquestra o fluxo da aplicação.
 * Captura entrada do usuário, consulta a API e persiste no banco de dados.
 */
public class Main {

    // Configurações do banco de dados
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/fornecedores";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     CADASTRO DE FORNECEDORES - BUSCA DE CNPJ              ║");
        System.out.println("║        Integração com BrasilAPI + PostgreSQL              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Inicializar DAO
        EmpresaDAO empresaDAO = new EmpresaDAO(DB_URL, DB_USER, DB_PASSWORD);

        // Testar conexão com banco de dados
        if (!empresaDAO.testarConexao()) {
            System.err.println("\n❌ Não foi possível conectar ao banco de dados.");
            System.err.println("Certifique-se de que:");
            System.err.println("  1. PostgreSQL está executando");
            System.err.println("  2. O banco 'fornecedores' foi criado");
            System.err.println("  3. As credenciais estão corretas");
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\n📋 OPÇÕES:");
                System.out.println("1 - Buscar e cadastrar fornecedor por CNPJ");
                System.out.println("2 - Buscar fornecedor cadastrado");
                System.out.println("3 - Sair");
                System.out.print("\nEscolha uma opção: ");

                String opcao = scanner.nextLine().trim();

                switch (opcao) {
                    case "1":
                        buscarECadastrar(scanner, empresaDAO);
                        break;
                    case "2":
                        buscarCadastrado(scanner, empresaDAO);
                        break;
                    case "3":
                        continuar = false;
                        System.out.println("\n👋 Aplicação encerrada. Até logo!");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }

            } catch (Exception e) {
                System.err.println("❌ Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    /**
     * Busca uma empresa pela API e a cadastra no banco de dados.
     */
    private static void buscarECadastrar(Scanner scanner, EmpresaDAO empresaDAO) {
        System.out.print("\n🔍 Digite o CNPJ (com ou sem formatação): ");
        String cnpjInput = scanner.nextLine().trim();

        if (cnpjInput.isEmpty()) {
            System.out.println("❌ CNPJ não pode ser vazio!");
            return;
        }

        try {
            // Verificar se já existe no banco
            String cnpjLimpo = cnpjInput.replaceAll("[^0-9]", "");
            if (empresaDAO.empresaExiste(cnpjLimpo)) {
                System.out.println("⚠️  Esta empresa já está cadastrada no banco de dados.");
                System.out.print("Deseja atualizar os dados? (S/N): ");
                String resposta = scanner.nextLine().trim().toUpperCase();
                if (!resposta.equals("S")) {
                    return;
                }
            }

            // Buscar dados da API
            System.out.println("\n⏳ Consultando BrasilAPI...");
            Empresa empresa = BrasilAPIClient.buscarEmpresa(cnpjInput);

            if (empresa == null) {
                System.out.println("❌ Empresa não encontrada na API ou ocorreu um erro.");
                return;
            }

            // Exibir dados recuperados
            exibirDadosEmpresa(empresa);

            // Confirmar cadastro
            System.out.print("\n✅ Deseja cadastrar esta empresa? (S/N): ");
            String confirmacao = scanner.nextLine().trim().toUpperCase();

            if (confirmacao.equals("S")) {
                boolean sucesso = empresaDAO.salvarEmpresa(empresa);
                if (sucesso) {
                    System.out.println("\n✅ Empresa cadastrada com sucesso!");
                    if (empresa.getQsa() != null && !empresa.getQsa().isEmpty()) {
                        System.out.println("📊 " + empresa.getQsa().size() + " sócio(s) também foram cadastrado(s).");
                    }
                } else {
                    System.out.println("\n❌ Erro ao cadastrar empresa no banco de dados.");
                }
            } else {
                System.out.println("⏭️  Cadastro cancelado.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("❌ CNPJ inválido: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar empresa: " + e.getMessage());
            if (e.getMessage().contains("UnknownHostException")) {
                System.err.println("   → Verifique sua conexão com a internet");
            }
            e.printStackTrace();
        }
    }

    /**
     * Busca uma empresa já cadastrada no banco de dados.
     */
    private static void buscarCadastrado(Scanner scanner, EmpresaDAO empresaDAO) {
        System.out.print("\n🔍 Digite o CNPJ da empresa a buscar: ");
        String cnpjInput = scanner.nextLine().trim();

        if (cnpjInput.isEmpty()) {
            System.out.println("❌ CNPJ não pode ser vazio!");
            return;
        }

        try {
            String cnpjLimpo = cnpjInput.replaceAll("[^0-9]", "");
            Empresa empresa = empresaDAO.buscarEmpresa(cnpjLimpo);

            if (empresa != null) {
                System.out.println("\n✅ Empresa encontrada!");
                exibirDadosEmpresa(empresa);
            } else {
                System.out.println("\n❌ Empresa não encontrada no banco de dados.");
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar empresa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exibe os dados da empresa de forma formatada.
     */
    private static void exibirDadosEmpresa(Empresa empresa) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("📊 DADOS DA EMPRESA:");
        System.out.println("═".repeat(60));
        System.out.println("CNPJ:                  " + empresa.getCnpj());
        System.out.println("Razão Social:          " + empresa.getRazaoSocial());
        System.out.println("Nome Fantasia:         " + empresa.getNomeFantasia());
        System.out.println("Logradouro:            " + empresa.getLogradouro());
        System.out.println("Número:                " + empresa.getNumero());
        if (empresa.getComplemento() != null && !empresa.getComplemento().isEmpty()) {
            System.out.println("Complemento:           " + empresa.getComplemento());
        }
        System.out.println("Bairro:                " + empresa.getBairro());
        System.out.println("Município:             " + empresa.getMunicipio());
        System.out.println("UF:                    " + empresa.getUf());
        System.out.println("CEP:                   " + empresa.getCep());
        System.out.println("CNAE Fiscal:           " + empresa.getCnaeFiscal());
        System.out.println("CNAE Descrição:        " + empresa.getCnaeFiscalDescricao());

        if (empresa.getQsa() != null && !empresa.getQsa().isEmpty()) {
            System.out.println("\n👥 SÓCIOS E ADMINISTRADORES (" + empresa.getQsa().size() + "):");
            System.out.println("─".repeat(60));
            for (int i = 0; i < empresa.getQsa().size(); i++) {
                var socio = empresa.getQsa().get(i);
                System.out.println((i + 1) + ". " + socio.getNomeSocio());
                System.out.println("   CPF/CNPJ: " + socio.getCnpjCpfDoSocio());
                System.out.println("   Qualificação: " + socio.getQualificacaoSocio());
            }
        }
        System.out.println("═".repeat(60));
    }
}