package com.example.jonathan.iot_smartcities_mobileapp2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.provider.DocumentFile;
import android.support.v4.util.Pair;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonathan on 19/08/2016.
 */
public class PIPEBoundaries {

    private Map<String, List<Double>> parametrosIncluded = new HashMap<String, List<Double>>();
    private Map<String, List<Double>> parametrosExcluded = new HashMap<String, List<Double>>();

    private int porcentagem = 0;

    private PIPEInstance PIPEInstance;

    PIPEBoundaries(PIPEInstance PIPEInstance){

        this.PIPEInstance = PIPEInstance;

        parametrosIncluded.put("temperatura", Arrays.asList(24.0, 29.0));
        parametrosIncluded.put("ph", Arrays.asList(7.0, 8.0));
        parametrosIncluded.put("salinidade", Arrays.asList(15.0, 30.0));

        parametrosExcluded.put("amonia", Arrays.asList(null, 0.1));
        parametrosExcluded.put("oxigenio", Arrays.asList(5.0, null));
        parametrosExcluded.put("nitrito", Arrays.asList(null, 76.0));
        parametrosExcluded.put("solidos", Arrays.asList(null, 10.0));
        parametrosExcluded.put("co2", Arrays.asList(null, 20.0));

        calculatePorcentagem();

    }

    public int getPorcentagem(){
        return porcentagem;
    }

    public void calculatePorcentagem(){
        PIPEInstance i = PIPEInstance;
        Map<String, Double> parametros = new HashMap<String,Double>();
        parametros.put("temperatura", i.getTemperatura());
        parametros.put("amonia", i.getAmonia());
        parametros.put("co2", i.getCo2());
        parametros.put("nitrito", i.getNitrito());
        parametros.put("oxigenio", i.getOxigenioDissolvido());
        parametros.put("ph", i.getPh());
        parametros.put("salinidade", i.getSalinidade());
        parametros.put("solidos", i.getSolidosSuspensos());

        int dentroDoControle = 0;
        int parametrosCount = 0;
        for (Map.Entry<String, Double> entry: parametros.entrySet()){
            parametrosCount += 1;
            if (parametrosIncluded.get(entry.getKey()) != null){
                List<Double> values = parametrosIncluded.get(entry.getKey());
                if (entry.getValue() >= values.get(0) && entry.getValue() <= values.get(1) ){
                    dentroDoControle += 1;
                }
            }
            else if (parametrosExcluded.get(entry.getKey()) != null){
                List<Double> values = parametrosExcluded.get(entry.getKey());
                if (values.get(0) == null){
                    if (entry.getValue() < values.get(1)){
                        dentroDoControle += 1;
                    }
                }
                else if (values.get(1) == null){
                    if (entry.getValue() > values.get(0)){
                        dentroDoControle += 1;
                    }
                }
                else {
                    if (entry.getValue() > values.get(0) && entry.getValue() < values.get(1)){
                        dentroDoControle += 1;
                    }
                }
            }
        }
        porcentagem = ( dentroDoControle * 100 ) / parametrosCount;
    }

    public int applyColor(String parameter, double value){

        if (parametrosIncluded.get(parameter) != null){
            List<Double> values = parametrosIncluded.get(parameter);
            if (value >= values.get(0) && value <= values.get(1)){
                return Color.GREEN;
            }
            else{
                return Color.RED;
            }
        }
        else if (parametrosExcluded.get(parameter) != null){
            List<Double> values = parametrosExcluded.get(parameter);
            if (values.get(0) == null){
                if (value < values.get(1)){
                    return Color.GREEN;
                }
                else{
                    return Color.RED;
                }
            }
            else if (values.get(1) == null){
                if (value > values.get(0)){
                    return Color.GREEN;
                }
                else{
                    return Color.RED;
                }
            }
            else {
                if (value > values.get(0) && value < values.get(1)){
                    return Color.GREEN;
                }
                else{
                    return Color.RED;
                }
            }
        }

        if (parameter.equals("porcentagem")){
            if (value <= 33) return Color.RED;
            else if (value <= 66) return Color.argb(255, 255, 165, 0);
            else if (value <= 99) return Color.YELLOW;
            else if (value == 100) return Color.GREEN;
        }

        return Color.BLACK;
    }

}
