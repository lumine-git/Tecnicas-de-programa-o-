import java.util.Scanner;

public class FaixaEtaria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite sua idade: ");
        int idade = sc.nextInt();

        if (idade < 18) {
            System.out.println("Menor de idade");
        } else if (idade < 60) {
            System.out.println("Adulto");
        } else {
            System.out.println("Idoso");
        }
        sc.close();
    }
}
