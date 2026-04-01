package service;

import model.EmissaoCarbono;

public class CalculadoraService {

    public double calcular(EmissaoCarbono dados) {
        double energia = dados.getConsumoEnergia() * 0.5;
        double transporte = dados.getKmRodados() * 0.2;
        double alimentacao = dados.getConsumoCarne() * 2.5;

        return energia + transporte + alimentacao;
    }
}