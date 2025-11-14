class Empresa {
    private Cliente[] clientes = new Cliente[5];
    private int totalClientes = 0;

    private Funcionario[] funcionarios = new Funcionario[10];
    private int totalFuncionarios = 0;

    public void adicionarCliente(String nome, String email) {
        if (totalClientes < clientes.length) {
            clientes[totalClientes++] = new Cliente(nome, email);
        }
    }

    public String exibirClientes() {
        StringBuilder sb = new StringBuilder("Clientes cadastrados:\n");
        for (int i = 0; i < totalClientes; i++) {
            sb.append(clientes[i].getNome()).append(" - ")
              .append(clientes[i].getEmail()).append("\n");
        }
        return sb.toString();
    }

    public void adicionarFuncionario(String nome, String cargo, double salario) {
        if (totalFuncionarios < funcionarios.length) {
            funcionarios[totalFuncionarios++] = new Funcionario(nome, cargo, salario);
        }
    }

    public String exibirFuncionarios() {
        StringBuilder sb = new StringBuilder("Funcionários cadastrados:\n");
        for (int i = 0; i < totalFuncionarios; i++) {
            sb.append(funcionarios[i].getNome()).append(" - ")
              .append(funcionarios[i].getCargo()).append(" - R$ ")
              .append(funcionarios[i].getSalario()).append("\n");
        }
        return sb.toString();
    }

    public double calcularFolhaSalarial() {
        double total = 0;
        for (int i = 0; i < totalFuncionarios; i++) {
            total += funcionarios[i].getSalario();
        }
        return total;
    }

    public double calcularMediaSalarial() {
        Calculadora calc = new Calculadora();
        double soma = 0;
        for (int i = 0; i < totalFuncionarios; i++) {
            soma = calc.somar(soma, funcionarios[i].getSalario());
        }
        return (totalFuncionarios > 0) ? soma / totalFuncionarios : 0;
    }

    public String exibirMediaSalarial() {
        return "Média salarial: R$ " + calcularMediaSalarial();
    }
}
