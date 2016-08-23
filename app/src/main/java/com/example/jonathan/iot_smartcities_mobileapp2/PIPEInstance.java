package com.example.jonathan.iot_smartcities_mobileapp2;

/**
 * Created by Jonathan on 11/08/2016.
 */
public class PIPEInstance {
    String timestamp;
    int position;
    double temperatura, amonia, oxigenioDissolvido, ph, nitrito, solidosSuspensos, co2, salinidade;
    public PIPEInstance(String timestamp, int position, double temperatura, double amonia, double oxigenioDissolvido, double ph
                            , double nitrito, double solidosSuspensos, double co2, double salinidade){
        this.timestamp = timestamp;
        this.position = position;
        this.temperatura = temperatura;
        this.amonia = amonia;
        this.oxigenioDissolvido = oxigenioDissolvido;
        this.ph = ph;
        this.nitrito = nitrito;
        this.solidosSuspensos = solidosSuspensos;
        this.co2 = co2;
        this.salinidade = salinidade;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getSpecificParameter(String parameter){
        if (parameter.toLowerCase().equals("temperatura")) return temperatura;
        else if (parameter.toLowerCase().equals("amonia")) return amonia;
        else if (parameter.toLowerCase().equals("oxigenio")) return oxigenioDissolvido;
        else if (parameter.toLowerCase().equals("ph")) return ph;
        else if (parameter.toLowerCase().equals("nitrito")) return nitrito;
        else if (parameter.toLowerCase().equals("solidos")) return solidosSuspensos;
        else if (parameter.toLowerCase().equals("co2")) return co2;
        else if (parameter.toLowerCase().equals("salinidade")) return salinidade;
        return 0;
    }

    public int getPosition(){
        return position;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getAmonia() {
        return amonia;
    }

    public double getOxigenioDissolvido() {
        return oxigenioDissolvido;
    }

    public double getPh() {
        return ph;
    }

    public double getNitrito() {
        return nitrito;
    }

    public double getSolidosSuspensos() {
        return solidosSuspensos;
    }

    public double getCo2() {
        return co2;
    }

    public double getSalinidade() {
        return salinidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PIPEInstance that = (PIPEInstance) o;

        return getTimestamp() != null ? getTimestamp().equals(that.getTimestamp()) : that.getTimestamp() == null;

    }

    @Override
    public int hashCode() {
        return 0;
    }
}
