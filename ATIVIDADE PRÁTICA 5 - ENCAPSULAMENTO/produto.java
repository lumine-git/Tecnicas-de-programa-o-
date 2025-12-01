package br.edu.fatecpg.atvLumine.model;

public class Produto {
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.setNome(nome);
        this.setPreco(preco);
        this.setQuantidadeEstoque(quantidadeEstoque);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco >= 0) this.preco = preco;
        else System.out.println("Preço inválido! Deve ser maior ou igual a 0.");
    }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque >= 0) this.quantidadeEstoque = quantidadeEstoque;
        else System.out.println("Quantidade inválida! Deve ser maior ou igual a 0.");
    }
}