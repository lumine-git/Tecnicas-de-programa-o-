import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CadastroForm extends JFrame {
    private JLabel lblNome, lblIdade, lblSexo, lblResumo;
    private JTextField txtNome;
    private JSpinner spnIdade;
    private JRadioButton rbMasculino, rbFeminino;
    private JButton btnEnviar;
    private ButtonGroup grupoSexo;

    public CadastroForm() {
        setTitle("Formulário de Cadastro");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        lblIdade = new JLabel("Idade:");
        spnIdade = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));

        lblSexo = new JLabel("Sexo:");
        rbMasculino = new JRadioButton("Masculino");
        rbFeminino = new JRadioButton("Feminino");
        grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);

        btnEnviar = new JButton("Enviar");
        lblResumo = new JLabel("Preencha os dados acima");

        // Ação do botão
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarDados();
            }
        });

        add(lblNome);
        add(txtNome);
        add(lblIdade);
        add(spnIdade);
        add(lblSexo);
        add(rbMasculino);
        add(new JLabel(""));
        add(rbFeminino);
        add(btnEnviar);
        add(lblResumo);

        setVisible(true);
    }

    private void enviarDados() {
        String nome = txtNome.getText().trim();
        int idade = (int) spnIdade.getValue();
        String sexo = rbMasculino.isSelected() ? "Masculino" :
                      rbFeminino.isSelected() ? "Feminino" : "";

        if (nome.isEmpty() || sexo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, idade, sexo);
        lblResumo.setText(cliente.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroForm());
    }
}
