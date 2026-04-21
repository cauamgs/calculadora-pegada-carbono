package service;

import model.EmissaoCarbono;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculadoraService {


    public Map<String, Double> calcularDetalhado(EmissaoCarbono dados) {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("energia",           calcularEnergia(dados));
        map.put("transporte",        calcularTransporte(dados));
        map.put("transportePublico", calcularTransportePublico(dados));
        map.put("alimentacao",       calcularAlimentacao(dados));
        map.put("voos",              calcularVoos(dados));
        map.put("consumo",           calcularConsumo(dados));
        map.put("residuos",          0.4);
        return map;
    }

    public double calcular(EmissaoCarbono dados) {
        return calcularDetalhado(dados).values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Energia: eletricidade (kWh/mês × 12 × 0,089 kgCO2/kWh) + gás GLP (m³/mês × 12 × 2,94 kgCO2/m³)
    private double calcularEnergia(EmissaoCarbono dados) {
        double eletricidade = dados.getConsumoEletricidade() * 12 * 0.089 / 1000.0;
        double gas          = dados.getConsumoGas()          * 12 * 2.94  / 1000.0;
        return eletricidade + gas;
    }

    // Transporte carro: km/ano ÷ consumo_médio (km/L) × emissão (kgCO2/L)
    // Fatores IPCC Tier 1 / ANP: Gasolina 2,31 kgCO2/L (12 km/L), Diesel 2,68 (14 km/L),
    //                             Etanol 1,54 (9 km/L), Flex 1,93 (11 km/L – média 50/50)
    private double calcularTransporte(EmissaoCarbono dados) {
        double kmAno      = dados.getKmRodados();
        String comb       = dados.getTipoCombustivel() != null
                            ? dados.getTipoCombustivel().toLowerCase() : "gasolina";
        switch (comb) {
            case "gasolina": return kmAno / 12.0 * 2.31 / 1000.0;
            case "diesel":   return kmAno / 14.0 * 2.68 / 1000.0;
            case "etanol":   return kmAno /  9.0 * 1.54 / 1000.0;
            case "flex":     return kmAno / 11.0 * 1.93 / 1000.0;
            default:         return kmAno / 12.0 * 2.31 / 1000.0;
        }
    }

    // Transporte público: km/semana × 52 semanas × 0,021 kgCO2/km
    private double calcularTransportePublico(EmissaoCarbono dados) {
        return dados.getKmTransportePublico() * 52 * 0.021 / 1000.0;
    }

    // Voos: km/ano × 0,133 kgCO2/km (fator médio ICAO adaptado para Brasil)
    private double calcularVoos(EmissaoCarbono dados) {
        return dados.getKmAviao() * 0.133 / 1000.0;
    }

    // Alimentação: fatores anuais do estudo WRI Brasil/Oxford 2024 (tCO2e/ano)
    // Alta carne vermelha: 2,5 | Carne moderada: 1,9 | Baixa carne: 1,4
    // Vegetariana: 1,0 | Vegana: 0,7
    private double calcularAlimentacao(EmissaoCarbono dados) {
        String dieta = dados.getConsumoCarne() != null
                       ? dados.getConsumoCarne().toLowerCase() : "moderada";
        switch (dieta) {
            case "alta":        return 2.5;
            case "moderada":    return 1.9;
            case "baixa":       return 1.4;
            case "vegetariana": return 1.0;
            case "vegana":      return 0.7;
            default:            return 1.9;
        }
    }

    // Consumo de bens: estimativa por nível declarado (tCO2e/ano)
    // Baixo 0,5 (compras essenciais/segunda mão) | Médio 1,0 | Alto 1,8 (compras frequentes)
    private double calcularConsumo(EmissaoCarbono dados) {
        String nivel = dados.getNivelConsumo() != null
                       ? dados.getNivelConsumo().toLowerCase() : "";
        if (nivel.contains("baixo")) return 0.5;
        if (nivel.contains("alto"))  return 1.8;
        return 1.0;
    }
}
