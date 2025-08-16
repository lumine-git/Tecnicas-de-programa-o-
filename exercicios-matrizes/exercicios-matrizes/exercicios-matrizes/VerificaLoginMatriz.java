import java.util.Scanner;

public class VerificaLoginMatriz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] usuarios = {
            {"Ale", "Java123"},
            {"Lumine", "Pokemon"},
            {"Vinicius", "Fotografia123"},
            {"Leonardo", "Corintia"},
            {"Ricardo", "ShadowOurico"},
            {"Marcos", "Gptelson"}
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

        if (autenticado) {
            System.out.println("Login feito!");
        } else {
            System.out.println("Login ou senha incorretos.");
        }
    }
}
