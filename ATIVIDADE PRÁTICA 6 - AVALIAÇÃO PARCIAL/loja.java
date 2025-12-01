public class Loja {

    String[] nomesProdutos = {"Notebook", "Mouse", "Teclado", "Monitor"};
    int[] quantidadeEstoque = {10, 25, 15, 8};
    double[] precosProdutos = {3500.00, 80.00, 150.00, 900.00};

    // Exibir 
    public void exibirProdutos() {
        System.out.println("=== ESTOQUE DA LOJA ===");
        for (int i = 0; i < nomesProdutos.length; i++) {
            System.out.println(nomesProdutos[i] + " | Preço: R$" + precosProdutos[i] + " | Quantidade: " + quantidadeEstoque[i]);
        }
    }

    // Produto mais caro e mais barato
    public void produtoMaisCaroBarato() {
        int indiceCaro = 0, indiceBarato = 0;

        for (int i = 0; i < precosProdutos.length; i++) {
            if (precosProdutos[i] > precosProdutos[indiceCaro]) indiceCaro = i;
            if (precosProdutos[i] < precosProdutos[indiceBarato]) indiceBarato = i;
        }

        System.out.println("\nProduto mais caro: " + nomesProdutos[indiceCaro] + " - R$" + precosProdutos[indiceCaro]);
        System.out.println("Produto mais barato: " + nomesProdutos[indiceBarato] + " - R$" + precosProdutos[indiceBarato]);
    }

    // Valor total em estoque
    public void valorTotalEstoque() {
        double total = 0;
        for (int i = 0; i < nomesProdutos.length; i++) {
            total += precosProdutos[i] * quantidadeEstoque[i];
        }
        System.out.println("\nValor total em estoque: R$" + total);
    }

    // Comprar 
    public void comprarProduto(String nome, int qtd) {
        for (int i = 0; i < nomesProdutos.length; i++) {
            if (nomesProdutos[i].equalsIgnoreCase(nome)) {
                if (quantidadeEstoque[i] >= qtd) {
                    quantidadeEstoque[i] -= qtd;
                    System.out.println("\nCompra realizada! Estoque atualizado.");
                } else System.out.println("\nQuantidade insuficiente no estoque!");
                return;
            }
        }
        System.out.println("\nProduto não encontrado!");
    }

    // Repor 
    public void reporProduto(String nome, int qtd) {
        for (int i = 0; i < nomesProdutos.length; i++) {
            if (nomesProdutos[i].equalsIgnoreCase(nome)) {
                quantidadeEstoque[i] += qtd;
                System.out.println("\nEstoque reposto com sucesso.");
                return;
            }
        }
        System.out.println("\nProduto não encontrado!");
    }

   