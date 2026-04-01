package controller;

import model.EmissaoCarbono;
import service.CalculadoraService;

public class CalculadoraController {

    public double calcular(double energia, double km, double carne) {
        EmissaoCarbono dados = new EmissaoCarbono(energia, km, carne);
        CalculadoraService service = new CalculadoraService();

        return service.calcular(dados);
    }
}