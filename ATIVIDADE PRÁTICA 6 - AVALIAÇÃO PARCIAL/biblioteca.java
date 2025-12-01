public class Biblioteca {

    String[] livros = {"Dom Casmurro", "1984", "O Hobbit"};
    int[] diasAtraso = {3, 10, 0};
    double[] multaPorDia = {1.5, 2.0, 1.0};

    // Mostrar livros
    public void mostrarLivros() {
        System.out.println("\n=== LIVROS ===");
        for (int i = 0; i < livros.length; i++) {
            System.out.println(livros[i] + " | Dias de atraso: " + diasAtraso[i] + " | Multa dia: R$" + multaPorDia[i]);
        }
    }

    // Multa total acumulada
    public void multaTotal() {
        double total = 0;
        for (int i = 0; i < livros.length; i++) {
            total += diasAtraso[i] * multaPorDia[i];
        }
        System.out.println("\nMulta total acumulada: R$" + total);
    }

    // Média de dias de atraso
    public void mediaAtraso() {
        int soma = 0, count = 0;
        for (int dias : diasAtraso) {
            if (dias > 0) {
                soma += dias;
                count++;
            }
        }
        System.out.println("\nMédia de atraso: " + (count > 0 ? (double)soma / count : 0));
    }

    // Emprestar (remover temporariamente)
    public void emprestarLivro(String nome) {
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null && livros[i].equalsIgnoreCase(nome)) {
                livros[i] = null;
                System.out.println("\nLivro emprestado com sucesso!");
                return;
            }
        }
        System.out.println("\nLivro não encontrado!");
    }

    // Devolver livro com atraso
    public void devolverLivro(String nome, int atraso, double multaDia) {
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] == null) {
                livros[i] = nome;
                diasAtraso[i] = atraso;
                multaPorDia[i] = multaDia;
                System.out.println("\nLivro devolvido e registrado com atraso.");
                return;
            }
        }
        System.out.println("\nSem espaço para registrar!");
    }

    
