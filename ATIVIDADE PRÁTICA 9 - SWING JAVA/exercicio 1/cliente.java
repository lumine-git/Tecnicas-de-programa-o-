public class Cliente {
    private String nome;
    private int idade;
    private String sexo;

    public Cliente(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Idade: " + idade + " | Sexo: " + sexo;
    }
}
