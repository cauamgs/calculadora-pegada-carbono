package controller;

import model.EmissaoCarbono;
import service.CalculadoraService;
import java.util.Map;

public class CalculadoraController {

    public Map<String, Double> calcularDetalhado(double eletricidade, double gas, double kmRodados,
            String tipoCombustivel, double kmTransportePublico,
            String consumoCarne, double kmAviao, String nivelConsumo) {
        EmissaoCarbono dados = new EmissaoCarbono(
                eletricidade, gas, kmRodados, tipoCombustivel,
                kmTransportePublico, consumoCarne, kmAviao, nivelConsumo);
        return new CalculadoraService().calcularDetalhado(dados);
    }
}
