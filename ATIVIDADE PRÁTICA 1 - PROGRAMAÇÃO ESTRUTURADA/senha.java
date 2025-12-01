import java.util.Scanner;

public class SenhaSecreta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String SENHA = "Java123";
        String tentativa = "";

        while (!tentativa.equals(SENHA)) {
            System.out.print("Digite a senha: ");
            tentativa = sc.nextLine();

            if (!tentativa.equals(SENHA)) {
                System.out.println("Senha incorreta, tente novamente!");
            }
        }
        System.out.println("Acesso permitido! Bem-vindo!");
        sc.close();
    }
}
