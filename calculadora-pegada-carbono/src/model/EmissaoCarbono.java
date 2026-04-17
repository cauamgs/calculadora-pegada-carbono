package model;

public class EmissaoCarbono {

    private double consumoEletricidade;
    private double kmRodados;
    private double kmTransportePublico;
    private String consumoCarne;
    private String tipoCombustivel;
    private String tipoVeiculo;
    private double kmAviao;
    private double consumoGas;

    public EmissaoCarbono(double consumoEletricidade, double kmRodados, String consumoCarne, String tipoCombustivel, String tipoVeiculo, double kmAviao, double consumoGas, double kmTransportePublico) {
        this.consumoEletricidade = consumoEletricidade;
        this.kmRodados = kmRodados;
        this.kmTransportePublico = kmTransportePublico;
        this.consumoCarne = consumoCarne;
        this.tipoCombustivel = tipoCombustivel;
        this.tipoVeiculo = tipoVeiculo;
        this.kmAviao = kmAviao;
        this.consumoGas = consumoGas;
    }

    public double getConsumoEletricidade() {
        return consumoEletricidade;
    }

    public void setConsumoEletricidade(double consumoEletricidade) {
        this.consumoEletricidade = consumoEletricidade;
    }

    public double getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(double kmRodados) {
        this.kmRodados = kmRodados;
    }

    public double getKmTransportePublico() {
        return kmTransportePublico;
    }

    public void setKmTransportePublico(double kmTransportePublico) {
        this.kmTransportePublico = kmTransportePublico;
    }

    public String getConsumoCarne() {
        return consumoCarne;
    }

    public void setConsumoCarne(String consumoCarne) {
        this.consumoCarne = consumoCarne;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public double getKmAviao() {
        return kmAviao;
    }

    public void setKmAviao(double kmAviao) {
        this.kmAviao = kmAviao;
    }

    public double getConsumoGas() {
        return consumoGas;
    }

    public void setConsumoGas(double consumoGas) {
        this.consumoGas = consumoGas;
    }
}