package br.com.cidandrade.extras;

/**
 * Converte entre escalas de temperatura
 *
 * @author cidandrade
 */
public class Temperatura {

    private float temperaturaEmKelvin;

    public Temperatura() {
        this.temperaturaEmKelvin = 0F;
    }

    public Temperatura(float temperaturaEmKelvin) {
        this.temperaturaEmKelvin = temperaturaEmKelvin;
    }

    public float getCelsius() {
        return temperaturaEmKelvin - 273.15F;
    }

    public float getKelvin() {
        return temperaturaEmKelvin;
    }

    public float getFahrenheit() {
        return temperaturaEmKelvin * 1.8F - 459.889F;
    }

    public float getRankine() {
        return temperaturaEmKelvin * 1.8F;
    }

    public float getRomer() {
        return (temperaturaEmKelvin - 273.15F) * 0.525F + 7.5F;
    }

    public float getNewton() {
        return (temperaturaEmKelvin - 273.15F) * 0.33F;
    }

    public float getDelisle() {
        return (373.15F - temperaturaEmKelvin) * 1.5F;
    }

    public float getReaumur() {
        return (temperaturaEmKelvin - 273.15F) * 0.8F;
    }

    public void setCelsius(float temperaturaEmCelsius) {
        this.temperaturaEmKelvin = temperaturaEmCelsius + 273.15F;
    }

    public void setKelvin(float temperaturaEmKelvin) {
        this.temperaturaEmKelvin = temperaturaEmKelvin;
    }

    public void setFahrenheit(float temperaturaEmFahrenheit) {
        this.temperaturaEmKelvin = (temperaturaEmFahrenheit + 459.67F) / 1.8F;
    }

    public void setRankine(float temperaturaEmRankine) {
        this.temperaturaEmKelvin = temperaturaEmRankine / 1.8F;
    }

    public void setRomer(float temperaturaEmRomer) {
        this.temperaturaEmKelvin = (temperaturaEmRomer - 7.5F)
                * 40F / 21F + 273.15F;
    }

    public void setNewton(float temperaturaEmNewton) {
        this.temperaturaEmKelvin = temperaturaEmNewton * 100F / 33F + 273.15F;
    }

    public void setDelisle(float temperaturaEmDelisle) {
        this.temperaturaEmKelvin = 373.15F - temperaturaEmKelvin * 2F / 3F;
    }

    public void setReaumur(float temperaturaEmReaumur) {
        this.temperaturaEmKelvin = temperaturaEmKelvin * 1.25F + 273.15F;
    }
}
