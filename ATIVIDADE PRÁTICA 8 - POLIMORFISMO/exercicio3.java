class Produto {
    String nome;
    double preco;
    int quantidade;

    public Produto(String nome) {
        this.nome = nome;
        this.preco = 0.0;
        this.quantidade = 0;
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = 0;
    }

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public void exibirInfo() {
        System.out.println("Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade);
    }
}

public class TesteProduto {
    public static void main(String[] args) {
        Produto p1 = new Produto("Caneta");
        Produto p2 = new Produto("Caderno", 12.50);
        Produto p3 = new Produto("Mochila", 150.0, 5);

        p1.exibirInfo();
        p2.exibirInfo();
        p3.exibirInfo();
    }
}
