package model;

public class EmissaoCarbono {

    private double consumoEnergia;
    private double kmRodados;
    private double consumoCarne;
    private String tipoCombustivel;

    public EmissaoCarbono(double consumoEnergia, double kmRodados, double consumoCarne, String tipoCombustivel) {
        this.consumoEnergia = consumoEnergia;
        this.kmRodados = kmRodados;
        this.consumoCarne = consumoCarne;
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public double getKmRodados() {return kmRodados;}

    public double getConsumoCarne() {
        return consumoCarne;
    }

    public String getTipoCombustivel() {return tipoCombustivel;}

    public void setConsumoEnergia(double consumoEnergia){
        this.consumoEnergia = consumoEnergia;
    }

    public void setKmRodados(double kmRodados){
        this.kmRodados = kmRodados;
    }

    public void setConsumoCarne(double consumoCarne){
        this.consumoCarne = consumoCarne;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

}