import java.util.Scanner;

public class MenuAreas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("MENU");
        System.out.println("1 - Calcular área do quadrado");
        System.out.println("2 - Calcular área do círculo");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            System.out.print("Digite o lado do quadrado: ");
            double lado = sc.nextDouble();
            System.out.println("Área = " + (lado * lado));
        } else if (opcao == 2) {
            System.out.print("Digite o raio do círculo: ");
            double raio = sc.nextDouble();
            System.out.println("Área = " + (Math.PI * raio * raio));
        } else {
            System.out.println("Opção inválida!");
        }
        sc.close();
    }
}

