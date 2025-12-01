import java.util.Scanner;

public class LoginMatriz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] usuarios = {
            {"ana", "123"},
            {"joao", "abc"},
            {"admin", "000"},
            {"maria", "pass"},
            {"user", "111"},
            {"teste", "999"}
        };

        System.out.print("Digite o login: ");
        String login = sc.nextLine();

        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();

        boolean autenticado = false;

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0].equals(login) && usuarios[i][1].equals(senha)) {
                autenticado = true;
                break;
            }
        }

        if (autenticado)
            System.out.println("Login realizado com sucesso!");
        else
            System.out.println("Login ou senha incorretos!");

        sc.close();
    }
}
