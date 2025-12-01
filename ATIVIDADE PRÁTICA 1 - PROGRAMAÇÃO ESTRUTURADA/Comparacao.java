import java.util.Scanner;

public class ComparacaoNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        int n1 = sc.nextInt();

        System.out.print("Digite o segundo número: ");
        int n2 = sc.nextInt();

        if (n1 == n2) {
            System.out.println("Os números são iguais.");
        } else {
            System.out.println("Os números são diferentes.");
            if (n1 > n2) {
                System.out.println("O primeiro é maior.");
            } else {
                System.out.println("O segundo é maior.");
            }
        }
        sc.close();
    }
}
