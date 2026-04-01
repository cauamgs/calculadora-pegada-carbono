package model;

public class EmissaoCarbono {

    private double consumoEnergia;
    private double kmRodados;
    private double consumoCarne;

    public EmissaoCarbono(double consumoEnergia, double kmRodados, double consumoCarne) {
        this.consumoEnergia = consumoEnergia;
        this.kmRodados = kmRodados;
        this.consumoCarne = consumoCarne;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public double getKmRodados() {
        return kmRodados;
    }

    public double getConsumoCarne() {
        return consumoCarne;
    }
}