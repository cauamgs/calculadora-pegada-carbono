package model;

public class EmissaoCarbono {

    private double consumoEnergia;
    private double kmRodados;
    private double consumoCarne;
    private String tipoCombustivel;
    private double consumoVeiculo;
    private String tipoVeiculo;
    private double kmAviao;

    public EmissaoCarbono(double consumoEnergia, double kmRodados, double consumoCarne, String tipoCombustivel, double consumoVeiculo, String tipoVeiculo, double kmAviao) {
        this.consumoEnergia = consumoEnergia;
        this.kmRodados = kmRodados;
        this.consumoCarne = consumoCarne;
        this.tipoCombustivel = tipoCombustivel;
        this.consumoVeiculo = consumoVeiculo;
        this.tipoVeiculo = tipoVeiculo;
        this.kmAviao = kmAviao;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public double getKmRodados() {return kmRodados;}

    public double getConsumoCarne() {
        return consumoCarne;
    }

    public String getTipoCombustivel() {return tipoCombustivel;}

    public double getConsumoVeiculo() {return consumoVeiculo;}

    public double getKmAviao() {return kmAviao;}

    public String getTipoVeiculo() {return tipoVeiculo;}

    public void setKmAviao(double kmAviao) {
        this.kmAviao = kmAviao;
    }

    public void setConsumoVeiculo(double consumoVeiculo) {
        this.consumoVeiculo = consumoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public void setKmRodados(double kmRodados) {
        this.kmRodados = kmRodados;
    }
}