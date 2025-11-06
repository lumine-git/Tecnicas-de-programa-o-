import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreferenciasUsuario extends JFrame {
    private JLabel lblTema, lblNotif, lblVolume;
    private JComboBox<String> comboTema;
    private JCheckBox chkNotif;
    private JSlider sliderVolume;
    private JButton btnSalvar;
    private JTextArea txtResumo;

    private Usuario usuario;

    public PreferenciasUsuario() {
        setTitle("Configurações do Usuário");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 5, 5));

        lblTema = new JLabel("Tema:");
        comboTema = new JComboBox<>(new String[]{"Claro", "Escuro"});

        lblNotif = new JLabel("Notificações:");
        chkNotif = new JCheckBox("Ativar notificações");

        lblVolume = new JLabel("Volume:");
        sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setMajorTickSpacing(20);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);

        btnSalvar = new JButton("Salvar");
        txtResumo = new JTextArea("Nenhuma preferência salva ainda.");
        txtResumo.setEditable(false);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPreferencias();
            }
        });

        comboTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTema();
            }
        });

        add(lblTema);
        add(comboTema);
        add(lblNotif);
        add(chkNotif);
        add(lblVolume);
        add(sliderVolume);
        add(btnSalvar);
        add(new JScrollPane(txtResumo));

        atualizarTema(); // Inicia com o tema selecionado
        setVisible(true);
    }

    private void atualizarTema() {
        String tema = (String) comboTema.getSelectedItem();
        if (tema.equals("Escuro")) {
            getContentPane().setBackground(Color.DARK_GRAY);
            txtResumo.setBackground(Color.GRAY);
            txtResumo.setForeground(Color.WHITE);
        } else {
            getContentPane().setBackground(Color.WHITE);
            txtResumo.setBackground(Color.LIGHT_GRAY);
            txtResumo.setForeground(Color.BLACK);
        }
    }

    private void salvarPreferencias() {
        String tema = (String) comboTema.getSelectedItem();
        boolean notificacoes = chkNotif.isSelected();
        int volume = sliderVolume.getValue();

        usuario = new Usuario(tema, notificacoes, volume);
        txtResumo.setText(usuario.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PreferenciasUsuario());
    }
}
