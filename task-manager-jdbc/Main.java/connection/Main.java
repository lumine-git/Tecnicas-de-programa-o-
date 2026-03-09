import dao.TaskDAO;
import model.Task;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskDAO dao = new TaskDAO();

        int option;

        do {

            System.out.println("\n--- GERENCIADOR DE TAREFAS ---");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Marcar como concluída");
            System.out.println("4 - Excluir tarefa");
            System.out.println("5 - Filtrar por categoria");
            System.out.println("6 - Filtrar por status");
            System.out.println("0 - Sair");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:

                    System.out.print("Título: ");
                    String title = sc.nextLine();

                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();

                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();

                    Task task = new Task(title, desc, cat, "PENDENTE");
                    dao.create(task);

                    break;

                case 2:

                    List<Task> tasks = dao.listAll();

                    tasks.forEach(t ->
                            System.out.println(t.getId() + " | "
                                    + t.getTitle() + " | "
                                    + t.getCategory() + " | "
                                    + t.getStatus()));

                    break;

                case 3:

                    System.out.print("ID da tarefa: ");
                    int id = sc.nextInt();

                    dao.updateStatus(id, "CONCLUIDA");

                    break;

                case 4:

                    System.out.print("ID da tarefa: ");
                    int deleteId = sc.nextInt();

                    dao.delete(deleteId);

                    break;

                case 5:

                    System.out.print("Categoria: ");
                    String category = sc.nextLine();

                    dao.filterByCategory(category)
                            .forEach(t -> System.out.println(t.getTitle()));

                    break;

                case 6:

                    System.out.print("Status: ");
                    String status = sc.nextLine();

                    dao.filterByStatus(status)
                            .forEach(t -> System.out.println(t.getTitle()));

                    break;
            }

        } while (option != 0);

    }
}