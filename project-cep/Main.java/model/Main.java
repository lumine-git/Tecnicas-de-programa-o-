import model.Endereco;
import service.ViaCepService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ViaCepService service = new ViaCepService();

        ArrayList<Endereco> listaEnderecos = new ArrayList<>();
        ArrayList<String> historico = new ArrayList<>();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== CONSULTA CEP =====");
            System.out.println("1 - Buscar endereço");
            System.out.println("2 - Excluir endereço");
            System.out.println("3 - Ver histórico");
            System.out.println("4 - Listar endereços");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    System.out.println("Digite o CEP:");
                    String cep = sc.nextLine();

                    boolean existe = false;

                    for (Endereco e : listaEnderecos) {

                        if (e.getCep().equals(cep)) {
                            System.out.println("Endereço já está na lista:");
                            System.out.println(e);
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {

                        Endereco endereco = service.buscarCep(cep);

                        if (endereco != null) {

                            listaEnderecos.add(endereco);
                            historico.add(cep);

                            System.out.println("Endereço encontrado:");
                            System.out.println(endereco);
                        }
                    }

                    break;

                case 2:

                    System.out.println("Digite o CEP para excluir:");
                    String cepExcluir = sc.nextLine();

                    listaEnderecos.removeIf(e -> e.getCep().equals(cepExcluir));

                    System.out.println("Endereço removido.");

                    break;

                case 3:

                    System.out.println("\n===== HISTÓRICO =====");

                    for (String h : historico) {
                        System.out.println(h);
                    }

                    break;

                case 4:

                    System.out.println("\n===== ENDEREÇOS NA LISTA =====");

                    for (Endereco e : listaEnderecos) {
                        System.out.println(e);
                    }

                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida");

            }
        }

        sc.close();
    }
}