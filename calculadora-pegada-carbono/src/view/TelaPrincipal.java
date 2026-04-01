package view;

import controller.CalculadoraController;

import javax.swing.*;

public class TelaPrincipal {

    public void criarTela() {

        JFrame frame = new JFrame("Calculadora de Pegada de Carbono");

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        JLabel labelEnergia = new JLabel("Energia:");
        labelEnergia.setBounds(20, 20, 100, 25);
        frame.add(labelEnergia);

        JTextField campoEnergia = new JTextField();
        campoEnergia.setBounds(120, 20, 200, 25);
        frame.add(campoEnergia);

        JLabel labelKm = new JLabel("Km rodados:");
        labelKm.setBounds(20, 60, 100, 25);
        frame.add(labelKm);

        JTextField campoKm = new JTextField();
        campoKm.setBounds(120, 60, 200, 25);
        frame.add(campoKm);

        JLabel labelCarne = new JLabel("Carne:");
        labelCarne.setBounds(20, 100, 100, 25);
        frame.add(labelCarne);

        JTextField campoCarne = new JTextField();
        campoCarne.setBounds(120, 100, 200, 25);
        frame.add(campoCarne);


        JButton botao = new JButton("Calcular");
        botao.setBounds(120, 150, 100, 30);
        frame.add(botao);


        botao.addActionListener(e -> {
            try {
                double energia = Double.parseDouble(campoEnergia.getText());
                double km = Double.parseDouble(campoKm.getText());
                double carne = Double.parseDouble(campoCarne.getText());

                CalculadoraController controller = new CalculadoraController();
                double resultado = controller.calcular(energia, km, carne);

                JOptionPane.showMessageDialog(frame, "Emissão: " + resultado + " kg CO2");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Digite valores válidos!");
            }
        });


        frame.setVisible(true);
    }
}