package view;

import controller.CalculadoraController;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Map;

public class TelaPrincipal {

    // ── Paleta de cores ───────────────────────────────────────────────────────────
    private static final Color FUNDO       = new Color(245, 247, 250);
    private static final Color CARD_BG     = Color.WHITE;
    private static final Color TEAL        = new Color(26, 115, 93);
    private static final Color VERDE_BTN   = new Color(67, 160, 71);
    private static final Color TEXTO_LABEL = new Color(70, 70, 70);
    private static final Color TEXTO_CAMPO = new Color(20, 20, 20);
    private static final Color BORDA_CAMPO = new Color(200, 200, 200);
    private static final Color BORDA_FOCO  = new Color(26, 115, 93);
    private static final Color BAR_BG      = new Color(220, 240, 230);

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel resultadoContainer;

    // ── Campos do formulário ──────────────────────────────────────────────────────
    private JTextField campoEletricidade;
    private JTextField campoGas;
    private JTextField campoKm;
    private JComboBox<String> comboCombustivel;
    private JTextField campoTransportePublico;
    private JComboBox<String> comboDieta;
    private JTextField campoVoos;
    private JComboBox<String> comboConsumo;


    public void criarTela() {
        frame = new JFrame("Calculadora de Pegada de Carbono");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(520, 720));

        cardLayout = new CardLayout();
        mainPanel  = new JPanel(cardLayout);
        mainPanel.setBackground(FUNDO);

        resultadoContainer = new JPanel(new BorderLayout());
        resultadoContainer.setBackground(FUNDO);

        mainPanel.add(criarPainelInicial(),     "inicio");
        mainPanel.add(criarPainelFormulario(),  "formulario");
        mainPanel.add(resultadoContainer,       "resultado");

        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setMinimumSize(new Dimension(420, 520));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private JPanel criarPainelInicial() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(FUNDO);
        panel.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JLabel emoji = new JLabel("🌍", SwingConstants.CENTER);
        emoji.setFont(new Font("SansSerif", Font.PLAIN, 80));
        emoji.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Pegada de Carbono", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(TEAL);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtituloLinha1 = new JLabel("Descubra o impacto das suas atividades", SwingConstants.CENTER);
        subtituloLinha1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtituloLinha1.setForeground(new Color(100, 100, 100));
        subtituloLinha1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtituloLinha2 = new JLabel("no clima e veja como reduzir o CO₂", SwingConstants.CENTER);
        subtituloLinha2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtituloLinha2.setForeground(new Color(100, 100, 100));
        subtituloLinha2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnIniciar = criarBotao("  Iniciar Calculo");
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.setMaximumSize(new Dimension(280, 48));
        btnIniciar.addActionListener(e -> cardLayout.show(mainPanel, "formulario"));

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rodape.setBackground(FUNDO);
        rodape.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel infoCred = new JLabel("Fatores SEEG/ONS 2026 · IPCC Tier 1 · WRI Brasil");
        infoCred.setFont(new Font("SansSerif", Font.PLAIN, 11));
        infoCred.setForeground(new Color(160, 160, 160));
        rodape.add(infoCred);

        panel.add(Box.createVerticalGlue());
        panel.add(emoji);
        panel.add(Box.createVerticalStrut(18));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtituloLinha1);
        panel.add(Box.createVerticalStrut(2));
        panel.add(subtituloLinha2);
        panel.add(Box.createVerticalStrut(40));
        panel.add(btnIniciar);
        panel.add(Box.createVerticalStrut(24));
        panel.add(rodape);
        panel.add(Box.createVerticalGlue());

        return panel;
    }


    private JPanel criarPainelFormulario() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(FUNDO);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(FUNDO);
        content.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JPanel card = criarCard("1", "Calcule Suas Emissoes");

        campoEletricidade    = addCampo(card, "Eletricidade Mensal (kWh)",     "200");
        campoGas             = addCampo(card, "Gas de Cozinha (m\u00B3/mes)",  "10");
        campoKm              = addCampo(card, "Carro \u2013 Km Rodados/Ano",   "10000");
        comboCombustivel     = addCombo(card, "Tipo de Combustivel", new String[]{
                "Gasolina", "Etanol", "Flex (50/50)", "Diesel"});
        campoTransportePublico = addCampo(card, "Transporte Publico (km/semana)", "50");
        comboDieta           = addCombo(card, "Tipo de Dieta", new String[]{
                "Alta em Carne Vermelha (5x+/semana)",
                "Carne Moderada (2-4x/semana)",
                "Baixa Carne (1x/semana, mais frango/peixe)",
                "Vegetariana (ovos e laticinios)",
                "Vegana (100% vegetais)"});
        campoVoos            = addCampo(card, "Voos Anuais (km total)",        "0");
        comboConsumo         = addCombo(card, "Nivel de Consumo", new String[]{
                "Baixo (compras essenciais, segunda mao)",
                "Medio (compras regulares)",
                "Alto (compras frequentes, produtos novos)"});

        JButton btnCalcular = criarBotao("  Calcular Minha Pegada de Carbono");
        btnCalcular.addActionListener(e -> calcular());

        content.add(card);
        content.add(Box.createVerticalStrut(16));
        content.add(btnCalcular);
        content.add(Box.createVerticalStrut(8));

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(FUNDO);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(12);

        wrapper.add(scroll, BorderLayout.CENTER);
        return wrapper;
    }


    private JPanel criarPainelResultadoContent(double total, Map<String, Double> det) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(FUNDO);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(FUNDO);
        content.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));


        JPanel cardTotal = new JPanel();
        cardTotal.setLayout(new BoxLayout(cardTotal, BoxLayout.Y_AXIS));
        cardTotal.setBackground(TEAL);
        cardTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        cardTotal.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TEAL, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel lblSuaPegada = new JLabel("  Sua Pegada de Carbono");
        lblSuaPegada.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblSuaPegada.setForeground(Color.WHITE);
        lblSuaPegada.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTotal = new JLabel(String.format("%.2f tCO\u2082e/ano", total));
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 34));
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);

        double mediaBrasil = 2.2;
        String txtComparacao;
        if (total > mediaBrasil) {
            txtComparacao = String.format("%.1fx acima da media brasileira (%.1f tCO\u2082e/ano)", total / mediaBrasil, mediaBrasil);
        } else {
            txtComparacao = String.format("Abaixo da media brasileira de %.1f tCO\u2082e/ano", mediaBrasil);
        }
        JLabel lblComparacao = new JLabel(txtComparacao);
        lblComparacao.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblComparacao.setForeground(new Color(180, 235, 210));
        lblComparacao.setAlignmentX(Component.LEFT_ALIGNMENT);

        int arvores = (int) Math.ceil(total * 1000.0 / 21.7);
        JLabel lblArvores = new JLabel(String.format("  %d arvores para compensar anualmente", arvores));
        lblArvores.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblArvores.setForeground(new Color(180, 235, 210));
        lblArvores.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardTotal.add(lblSuaPegada);
        cardTotal.add(Box.createVerticalStrut(6));
        cardTotal.add(lblTotal);
        cardTotal.add(Box.createVerticalStrut(8));
        cardTotal.add(lblComparacao);
        cardTotal.add(Box.createVerticalStrut(4));
        cardTotal.add(lblArvores);

        JPanel cardDet = criarCard("2", "Detalhamento por Categoria");

        String[] nomes  = {"Energia",           "Transporte Carro",   "Transp. Publico",
                           "Alimentacao",        "Voos",               "Consumo",     "Residuos"};
        String[] emojis = {"\u26A1",             "\uD83D\uDE97",       "\uD83D\uDE8C",
                           "\uD83C\uDF7D",       "\u2708",             "\uD83D\uDECD", "\uD83D\uDDD1"};
        String[] keys   = {"energia",            "transporte",         "transportePublico",
                           "alimentacao",        "voos",               "consumo",      "residuos"};

        for (int i = 0; i < keys.length; i++) {
            double valor = det.getOrDefault(keys[i], 0.0);
            double pct   = total > 0 ? (valor / total) * 100.0 : 0.0;
            addLinhaResultado(cardDet,
                    emojis[i] + "  " + nomes[i],
                    String.format("%.3f tCO\u2082e  (%.0f%%)", valor, pct),
                    valor, total);
        }

        JButton btnFinalizar = criarBotao("  Finalizar");
        btnFinalizar.addActionListener(e -> cardLayout.show(mainPanel, "inicio"));

        JButton btnRecalcular = new JButton("  Recalcular");
        btnRecalcular.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnRecalcular.setBackground(Color.WHITE);
        btnRecalcular.setForeground(TEAL);
        btnRecalcular.setFocusPainted(false);
        btnRecalcular.setOpaque(true);
        btnRecalcular.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRecalcular.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnRecalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRecalcular.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TEAL, 2, true),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)));
        btnRecalcular.addActionListener(e -> cardLayout.show(mainPanel, "formulario"));

        content.add(cardTotal);
        content.add(Box.createVerticalStrut(14));
        content.add(cardDet);
        content.add(Box.createVerticalStrut(18));
        content.add(btnFinalizar);
        content.add(Box.createVerticalStrut(8));
        content.add(btnRecalcular);
        content.add(Box.createVerticalStrut(8));

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(FUNDO);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(12);

        wrapper.add(scroll, BorderLayout.CENTER);
        return wrapper;
    }


    private void calcular() {
        try {
            double eletricidade      = parseField(campoEletricidade);
            double gas               = parseField(campoGas);
            double km                = parseField(campoKm);
            String combustivel       = extrairCombustivel((String) comboCombustivel.getSelectedItem());
            double transportePublico = parseField(campoTransportePublico);
            String dieta             = extrairDieta((String) comboDieta.getSelectedItem());
            double voos              = parseField(campoVoos);
            String consumo           = extrairConsumo((String) comboConsumo.getSelectedItem());

            CalculadoraController controller = new CalculadoraController();
            Map<String, Double> detalhamento = controller.calcularDetalhado(
                    eletricidade, gas, km, combustivel,
                    transportePublico, dieta, voos, consumo);

            double total = detalhamento.values().stream().mapToDouble(Double::doubleValue).sum();

            resultadoContainer.removeAll();
            resultadoContainer.add(criarPainelResultadoContent(total, detalhamento), BorderLayout.CENTER);
            resultadoContainer.revalidate();
            resultadoContainer.repaint();

            cardLayout.show(mainPanel, "resultado");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Por favor, preencha os campos numericos com valores validos.",
                    "Atencao", JOptionPane.WARNING_MESSAGE);
        }
    }

    private double parseField(JTextField field) {
        String txt = field.getText().trim();
        if (txt.isEmpty()) return 0.0;
        return Double.parseDouble(txt.replace(",", "."));
    }

    private String extrairCombustivel(String item) {
        if (item == null) return "gasolina";
        String s = item.toLowerCase();
        if (s.contains("diesel"))   return "diesel";
        if (s.contains("etanol"))   return "etanol";
        if (s.contains("flex"))     return "flex";
        return "gasolina";
    }

    private String extrairDieta(String item) {
        if (item == null) return "moderada";
        String s = item.toLowerCase();
        if (s.contains("vegana"))        return "vegana";
        if (s.contains("vegetariana"))   return "vegetariana";
        if (s.contains("baixa"))         return "baixa";
        if (s.contains("alta"))          return "alta";
        return "moderada";
    }

    private String extrairConsumo(String item) {
        if (item == null) return "medio";
        String s = item.toLowerCase();
        if (s.contains("baixo")) return "baixo";
        if (s.contains("alto"))  return "alto";
        return "medio";
    }


    private JPanel criarCard(String numero, String titulo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BG);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(215, 215, 215), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 12, 20)));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        header.setBackground(CARD_BG);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel badge = new JLabel(numero, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(TEAL);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setFont(new Font("SansSerif", Font.BOLD, 13));
        badge.setForeground(Color.WHITE);
        badge.setOpaque(false);
        badge.setPreferredSize(new Dimension(28, 28));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTitulo.setForeground(new Color(25, 25, 25));

        header.add(badge);
        header.add(lblTitulo);
        card.add(header);
        card.add(Box.createVerticalStrut(16));
        return card;
    }

    private JTextField addCampo(JPanel card, String label, String valorPadrao) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setBackground(CARD_BG);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 72));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(TEXTO_LABEL);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField field = new JTextField(valorPadrao);
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setBackground(Color.WHITE);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDA_CAMPO, 1, true),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)));

        final String placeholder = valorPadrao;

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXTO_CAMPO);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(BORDA_FOCO, 2, true),
                        BorderFactory.createEmptyBorder(3, 9, 3, 9)));
            }
            @Override public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(BORDA_CAMPO, 1, true),
                        BorderFactory.createEmptyBorder(4, 10, 4, 10)));
            }
        });

        row.add(lbl);
        row.add(Box.createVerticalStrut(4));
        row.add(field);
        row.add(Box.createVerticalStrut(12));
        card.add(row);
        return field;
    }

    private JComboBox<String> addCombo(JPanel card, String label, String[] opcoes) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setBackground(CARD_BG);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 72));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(TEXTO_LABEL);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComboBox<String> combo = new JComboBox<>(opcoes);
        combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);

        row.add(lbl);
        row.add(Box.createVerticalStrut(4));
        row.add(combo);
        row.add(Box.createVerticalStrut(12));
        card.add(row);
        return combo;
    }

    private void addLinhaResultado(JPanel card, String categoria, String valor,
                                   double itemVal, double total) {
        JPanel outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
        outer.setBackground(CARD_BG);
        outer.setAlignmentX(Component.LEFT_ALIGNMENT);
        outer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        JPanel textRow = new JPanel(new BorderLayout());
        textRow.setBackground(CARD_BG);
        textRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblCat = new JLabel(categoria);
        lblCat.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblCat.setForeground(TEXTO_LABEL);

        JLabel lblVal = new JLabel(valor);
        lblVal.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblVal.setForeground(TEAL);

        textRow.add(lblCat, BorderLayout.WEST);
        textRow.add(lblVal, BorderLayout.EAST);

        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(total > 0 ? (int) Math.round((itemVal / total) * 100.0) : 0);
        bar.setStringPainted(false);
        bar.setForeground(TEAL);
        bar.setBackground(BAR_BG);
        bar.setBorderPainted(false);
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 6));
        bar.setAlignmentX(Component.LEFT_ALIGNMENT);

        outer.add(textRow);
        outer.add(Box.createVerticalStrut(5));
        outer.add(bar);
        outer.add(Box.createVerticalStrut(10));

        card.add(outer);
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(VERDE_BTN);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }
}
