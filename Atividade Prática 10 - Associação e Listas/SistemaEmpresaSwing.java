import javax.swing.*;
import java.awt.*;

public class SistemaEmpresaSwing extends JFrame {
    Empresa empresa = new Empresa();

    public SistemaEmpresaSwing() {
        setTitle("Sistema Empresa");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        JButton btnAddCliente = new JButton("Adicionar Cliente");
        JButton btnAddFuncionario = new JButton("Adicionar Funcionário");
        JButton btnExibirClientes = new JButton("Exibir Clientes");
        JButton btnExibirFuncionarios = new JButton("Exibir Funcionários");
        JButton btnFolha = new JButton("Calcular Folha Salarial");
        JButton btnMedia = new JButton("Exibir Média Salarial");

        add(btnAddCliente);
        add(btnAddFuncionario);
        add(btnExibirClientes);
        add(btnExibirFuncionarios);
        add(btnFolha);
        add(btnMedia);

        btnAddCliente.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome do cliente:");
            String email = JOptionPane.showInputDialog("Email do cliente:");
            empresa.adicionarCliente(nome, email);
        });

        btnAddFuncionario.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome do funcionário:");
            String cargo = JOptionPane.showInputDialog("Cargo:");
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Salário:"));
            empresa.adicionarFuncionario(nome, cargo, salario);
        });

        btnExibirClientes.addActionListener(e ->
                JOptionPane.showMessageDialog(null, empresa.exibirClientes()));

        btnExibirFuncionarios.addActionListener(e ->
                JOptionPane.showMessageDialog(null, empresa.exibirFuncionarios()));

        btnFolha.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "Folha salarial total: R$ " + empresa.calcularFolhaSalarial()));

        btnMedia.addActionListener(e ->
                JOptionPane.showMessageDialog(null, empresa.exibirMediaSalarial()));

        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaEmpresaSwing();
    }
}
