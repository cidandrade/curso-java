package br.com.cidandrade.extras;

/**
 *
 * @author cidandrade
 */
public class TemperaturaApp {

    public static void main(String[] args) {
        Temperatura temp = new Temperatura(184F);
        System.out.println("Kelvin: " + temp.getKelvin() + "ºK");
        System.out.println("Celsius: " + temp.getCelsius() + "ºC");
        System.out.println("Fahrenheit: " + temp.getFahrenheit() + "ºF");
        System.out.println("Rankine: " + temp.getRankine() + "ºRa");
        System.out.println("R\u00D8mer: " + temp.getRomer() + "ºR\u00D8");
        System.out.println("Newton: " + temp.getNewton() + "ºN");
        System.out.println("Delisle: " + temp.getDelisle() + "ºDe");
        System.out.println("Reaumur: " + temp.getReaumur() + "ºRé");
    }

}
