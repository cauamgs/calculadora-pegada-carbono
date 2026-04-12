package service;

import model.EmissaoCarbono;

public class CalculadoraService {

    public double calcular(EmissaoCarbono dados) {
        return calcularEnergia(dados) + calcularTransporte(dados) + calcularCarne(dados) + calcularAviao(dados);
    }

    private double calcularEnergia(EmissaoCarbono dados) {
        return dados.getConsumoEnergia() * 0.5;
    }

    private double calcularTransporte(EmissaoCarbono dados) {

        if (dados.getConsumoVeiculo() == 0) {
            throw new IllegalArgumentException("Consumo do veículo não pode ser zero");
        }

        double litros = dados.getKmRodados() / dados.getConsumoVeiculo();

        String tipoVeiculo = dados.getTipoVeiculo().toLowerCase();
        String combustivel = dados.getTipoCombustivel().toLowerCase();

        if (tipoVeiculo.equals("carro")) {
            switch (combustivel) {
                case "gasolina":
                    return litros * 2.3;
                case "diesel":
                    return litros * 2.6;
                case "etanol":
                    return litros * 1.5;
                default:
                    throw new IllegalArgumentException("Combustível inválido");
            }
        }
        else if (tipoVeiculo.equals("moto")) {
            switch (combustivel) {
                case "gasolina":
                    return litros * 2.3;
                case "diesel":
                    return litros * 2.6;
                case "etanol":
                    return litros * 1.5;
                default:
                    throw new IllegalArgumentException("Combustível inválido");
            }
        }

        else if (tipoVeiculo.equals("onibus")) {
            return litros * 1.8;
        }

        else if (tipoVeiculo.equals("trem") || tipoVeiculo.equals("metrô")) {
            return litros * 0.05;
        }

        return 0;
    }

    private double calcularCarne(EmissaoCarbono dados) {
        return dados.getConsumoCarne() * 27;
    }

    private double calcularAviao(EmissaoCarbono dados) {
        double km = dados.getKmAviao();

        if (km <= 1500) {
            return km * 0.15;
        } else {
            return km * 0.11;
        }
    }
}