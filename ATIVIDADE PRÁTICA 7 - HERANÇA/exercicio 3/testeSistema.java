import java.util.Scanner;

public class TesteSistema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaDeSeguranca sistema = new SistemaDeSeguranca();

        boolean logado = false;

        // Continua pedindo até o login ser bem-sucedido
        while (!logado) {
            System.out.print("👤 Usuário: ");
            String usuario = sc.nextLine();

            System.out.print("🔑 Senha: ");
            String senha = sc.nextLine();

            logado = sistema.login(usuario, senha);
        }

        System.out.println("\n🎉 Bem-vindo ao sistema, admin!\n");

        // Exemplo de logout
        System.out.print("Deseja fazer logout? (s/n): ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            sistema.logout();
        } else {
            System.out.println("Sessão mantida.");
        }

        sc.close();
    }
}
