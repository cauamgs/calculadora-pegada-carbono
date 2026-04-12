package controller;

import model.EmissaoCarbono;
import service.CalculadoraService;

public class CalculadoraController {

    public double calcular(double energia, double km, double carne, String tipoCombustivel, double consumoVeiculo, String tipoVeiculo, double kmAviao) {
        EmissaoCarbono dados = new EmissaoCarbono(energia, km, carne, tipoCombustivel, consumoVeiculo, tipoVeiculo, kmAviao);
        CalculadoraService service = new CalculadoraService();

        return service.calcular(dados);
    }
}