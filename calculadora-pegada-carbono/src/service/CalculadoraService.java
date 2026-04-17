package service;

import model.EmissaoCarbono;

public class CalculadoraService {

    public double calcular(EmissaoCarbono dados) {
        return calcularEnergia(dados) + calcularTransporte(dados) + calcularAviao(dados) + calcularTransportePublico(dados) + calcularAlimentacao(dados);
    }

    private double calcularEnergia(EmissaoCarbono dados) {
        double eletricidade = dados.getConsumoEletricidade() * 12 * 0.089;
        double gas = dados.getConsumoGas() * 12 * 2.94;
        return eletricidade + gas;
    }

    private double calcularTransporte(EmissaoCarbono dados) {
        double litros = dados.getKmRodados();

        String combustivel = dados.getTipoCombustivel().toLowerCase();

        switch (combustivel) {
            case "gasolina":
                return litros * (2.31 / 12);
            case "diesel":
                return litros * (2.68 / 14);
            case "etanol":
                return litros * (1.54 / 9);
            case "flex":
                return litros * (1.93 / 11);
        }
        return 0;
    }

    private double calcularTransportePublico(EmissaoCarbono dados) {
        double km_semanal_tp = dados.getKmTransportePublico();

        return km_semanal_tp * 52 * 0.021;
    }

    private double calcularAviao(EmissaoCarbono dados) {
        double km_ano = dados.getKmAviao();

        return km_ano * (0.133 + 0.111);
    }

    private double calcularAlimentacao(EmissaoCarbono dados) {
        String alimentacao = dados.getConsumoCarne().toLowerCase();

        switch (alimentacao) {
            case "alta":
                return 2.5;
            case "moderada":
                return 1.9;
            case "baixa":
                return 1.4;
            case "vegetariana":
                return 1.0;
            case "vegana":
                return 0.7;
        }
        return 0;
    }

}