package view;

import controller.CalculadoraController;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TelaPrincipal {

    private static final Color VERDE_ESCURO  = new Color(59, 109, 17);
    private static final Color VERDE_MEDIO   = new Color(99, 153, 34);
    private static final Color VERDE_CLARO   = new Color(234, 243, 222);
    private static final Color VERDE_BORDA   = new Color(151, 196, 89);
    private static final Color FUNDO         = new Color(247, 251, 242);

    public void criarTela() {
        JFrame frame = new JFrame("Calculadora de Pegada de Carbono");
        frame.setSize(480, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(FUNDO);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(FUNDO);
        root.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        header.setBackground(FUNDO);
        JLabel titulo = new JLabel("🌿 Calculadora de Pegada de Carbono");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        titulo.setForeground(VERDE_ESCURO);
        JLabel subtitulo = new JLabel("Estime suas emissões de CO₂ mensais");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitulo.setForeground(VERDE_MEDIO);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(titulo);
        header.add(subtitulo);
        root.add(header);
        root.add(Box.createVerticalStrut(16));

        JPanel cardTransporte = criarCard("⚡ Energia & Transporte");
        JTextField campoEnergia     = addCampo(cardTransporte, "Energia (kWh/mês):", "ex: 150");
        JTextField campoKm          = addCampo(cardTransporte, "Km rodados/mês:", "ex: 800");
        JComboBox<String> comboComb = addCombo(cardTransporte, "Combustível:",
                new String[]{"Gasolina", "Etanol", "Diesel", "Elétrico", "GNV"});
        root.add(cardTransporte);
        root.add(Box.createVerticalStrut(10));

        JPanel cardAlim = criarCard("🥩 Alimentação");
        JTextField campoCarne = addCampo(cardAlim, "Consumo de carne (kg/mês):", "ex: 4");
        root.add(cardAlim);
        root.add(Box.createVerticalStrut(14));

        JButton botao = new JButton("Calcular emissões");
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        botao.setBackground(VERDE_ESCURO);
        botao.setForeground(VERDE_CLARO);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);
        botao.setAlignmentX(Component.LEFT_ALIGNMENT);
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        root.add(botao);

        botao.addActionListener(e -> {
            try {
                double energia         = Double.parseDouble(campoEnergia.getText());
                double km              = Double.parseDouble(campoKm.getText());
                double carne           = Double.parseDouble(campoCarne.getText());
                String tipoCombustivel = (String) comboComb.getSelectedItem();

                CalculadoraController controller = new CalculadoraController();
                double resultado = controller.calcular(energia, km, carne, tipoCombustivel, 2, "teste", 4);

                int arvores = (int) Math.ceil(resultado / 21.7);
                JOptionPane.showMessageDialog(frame,
                        String.format("🌿 Emissão estimada: %.1f kg CO₂/mês%n"
                                + "Equivale a %d árvore(s) para compensar.", resultado, arvores),
                        "Resultado", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Por favor, preencha todos os campos com valores válidos.",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.add(new JScrollPane(root));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel criarCard(String titulo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(VERDE_BORDA, 1, true),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));

        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(VERDE_ESCURO);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        card.add(lbl);
        return card;
    }

    private JTextField addCampo(JPanel card, String label, String placeholder) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(new Color(80, 80, 80));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(Color.GRAY);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText(""); field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder); field.setForeground(Color.GRAY);
                }
            }
        });

        card.add(lbl);
        card.add(Box.createVerticalStrut(3));
        card.add(field);
        card.add(Box.createVerticalStrut(8));
        return field;
    }

    private JComboBox<String> addCombo(JPanel card, String label, String[] opcoes) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(new Color(80, 80, 80));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComboBox<String> combo = new JComboBox<>(opcoes);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lbl);
        card.add(Box.createVerticalStrut(3));
        card.add(combo);
        card.add(Box.createVerticalStrut(8));
        return combo;
    }
}