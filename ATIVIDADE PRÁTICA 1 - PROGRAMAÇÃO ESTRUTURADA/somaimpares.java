import java.util.Scanner;

public class SomaImpares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número inteiro n: ");
        int n = sc.nextInt();

        int soma = 0;
        int contador = 0;
        int numero = 1;

        while (contador < n) {
            soma += numero;
            numero += 2;
            contador++;
        }

        System.out.println("Soma dos " + n + " primeiros ímpares = " + soma);
        sc.close();
    }
}
