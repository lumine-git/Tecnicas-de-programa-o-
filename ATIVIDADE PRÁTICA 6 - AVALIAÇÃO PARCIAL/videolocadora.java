public class Videolocadora {
    
    String[] filmes = {"Avatar", "Matrix", "Interestelar", "Vingadores"};
    int[] vezesAlugado = {10, 7, 15, 20};
    double[] precosLocacao = {12.00, 8.00, 10.00, 15.00};

    // Listar filmes
    public void listarFilmes() {
        System.out.println("\n=== FILMES DISPONÍVEIS ===");
        for (int i = 0; i < filmes.length; i++) {
            System.out.println(filmes[i] + " | Preço: R$" + precosLocacao[i] + " | Alugado: " + vezesAlugado[i] + "x");
        }
    }

    // Faturamento total se todos fossem alugados uma vez
    public void faturamentoTotalEsperado() {
        double total = 0;
        for (double preco : precosLocacao) total += preco;
        System.out.println("\nFaturamento se todos fossem alugados uma vez: R$" + total);
    }

    // Filme mais alugado
    public void filmeMaisAlugado() {
        int indice = 0;
        for (int i = 1; i < vezesAlugado.length; i++) {
            if (vezesAlugado[i] > vezesAlugado[indice]) indice = i;
        }
        System.out.println("\nFilme mais alugado: " + filmes[indice] + " (" + vezesAlugado[indice] + " vezes)");
    }

    // Alugar
    public void alugarFilme(String nome) {
        for (int i = 0; i < filmes.length; i++) {
            if (filmes[i].equalsIgnoreCase(nome)) {
                vezesAlugado[i]++;
                System.out.println("\nFilme alugado com sucesso!");
                return;
            }
        }
        System.out.println("\nFilme não encontrado!");
    }

    // Devolver
    public void devolverFilme(String nome) {
        System.out.println("\nFilme devolvido: " + nome);
    }

    public static void main(String[] args) {
        Videolocadora v = new Videolocadora();
        v.listarFilmes();
        v.faturamentoTotalEsperado();
        v.filmeMaisAlugado();
        v.alugarFilme("Matrix");
        v.listarFilmes();
        v.devolverFilme("Avatar");
    }
}
(nome)) {
                quantidadeEstoque[i] += qtd;
                System.out.println("\nEstoque reposto com sucesso.");
                return;
            }
        }
        System.out.println("\nProduto não encontrado!");
    }

   