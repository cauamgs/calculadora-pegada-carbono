package model;

public class EmissaoCarbono {

    private double consumoEletricidade; // kWh/mês
    private double consumoGas;          // m³/mês
    private double kmRodados;           // km/ano
    private String tipoCombustivel;     // gasolina, etanol, flex, diesel
    private double kmTransportePublico; // km/semana
    private String consumoCarne;        // alta, moderada, baixa, vegetariana, vegana
    private double kmAviao;             // km/ano (total anual)
    private String nivelConsumo;        // baixo, médio, alto

    public EmissaoCarbono(double consumoEletricidade, double consumoGas, double kmRodados,
                          String tipoCombustivel, double kmTransportePublico,
                          String consumoCarne, double kmAviao, String nivelConsumo) {
        this.consumoEletricidade = consumoEletricidade;
        this.consumoGas = consumoGas;
        this.kmRodados = kmRodados;
        this.tipoCombustivel = tipoCombustivel;
        this.kmTransportePublico = kmTransportePublico;
        this.consumoCarne = consumoCarne;
        this.kmAviao = kmAviao;
        this.nivelConsumo = nivelConsumo;
    }

    public double getConsumoEletricidade() { return consumoEletricidade; }
    public void setConsumoEletricidade(double v) { this.consumoEletricidade = v; }

    public double getConsumoGas() { return consumoGas; }
    public void setConsumoGas(double v) { this.consumoGas = v; }

    public double getKmRodados() { return kmRodados; }
    public void setKmRodados(double v) { this.kmRodados = v; }

    public String getTipoCombustivel() { return tipoCombustivel; }
    public void setTipoCombustivel(String v) { this.tipoCombustivel = v; }

    public double getKmTransportePublico() { return kmTransportePublico; }
    public void setKmTransportePublico(double v) { this.kmTransportePublico = v; }

    public String getConsumoCarne() { return consumoCarne; }
    public void setConsumoCarne(String v) { this.consumoCarne = v; }

    public double getKmAviao() { return kmAviao; }
    public void setKmAviao(double v) { this.kmAviao = v; }

    public String getNivelConsumo() { return nivelConsumo; }
    public void setNivelConsumo(String v) { this.nivelConsumo = v; }
}
