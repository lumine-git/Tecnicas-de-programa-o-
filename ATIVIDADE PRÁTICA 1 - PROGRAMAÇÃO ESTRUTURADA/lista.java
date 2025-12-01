import java.util.Scanner;

public class ListaNomes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nomes = new String[5];

        for (int i = 0; i < nomes.length; i++) {
            System.out.print("Digite o nome " + (i+1) + ": ");
            nomes[i] = sc.nextLine();
        }

        System.out.print("Digite outro nome para procurar na lista: ");
        String nomeProcurado = sc.nextLine();

        boolean encontrado = false;

        for (String nome : nomes) {
            if (nome.equalsIgnoreCase(nomeProcurado)) {
                encontrado = true;
                break;
            }
        }

        if (encontrado)
            System.out.println("O nome está na lista!");
        else
            System.out.println("O nome NÃO está na lista!");

        sc.close();
    }
}
