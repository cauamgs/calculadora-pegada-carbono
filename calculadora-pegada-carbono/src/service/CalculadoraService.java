package service;

import model.EmissaoCarbono;

public class CalculadoraService {

    public double calcular(EmissaoCarbono dados) {
        return calcularEnergia(dados) + calcularTransporte(dados);
    }

    private double calcularEnergia(EmissaoCarbono dados) {
        return dados.getConsumoEnergia()*0.5;
    }

    private double calcularTransporte(EmissaoCarbono dados) {
        switch (dados.getTipoCombustivel().toLowerCase()) {
            case "gasolina":
                return dados.getKmRodados() * 0.12;
            case "diesel":
                return dados.getKmRodados() * 0.15;
            case "etanol":
                return dados.getKmRodados() * 0.10;
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}