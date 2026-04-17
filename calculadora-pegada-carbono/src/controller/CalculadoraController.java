package controller;

import model.EmissaoCarbono;
import service.CalculadoraService;

public class CalculadoraController {

    public double calcular(double energia, double km, String carne, String tipoCombustivel, String tipoVeiculo, double kmAviao, double consumoGas, double kmTransportePublico) {
        EmissaoCarbono dados = new EmissaoCarbono(energia, km, carne, tipoCombustivel, tipoVeiculo, kmAviao, consumoGas, kmTransportePublico);
        CalculadoraService service = new CalculadoraService();

        return service.calcular(dados);
    }
}