package br.com.cidandrade.aulas;

/**
 *
 * @author cidandrade
 */
public class SerHumanoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SerHumano asdrubal = new SerHumano();
        asdrubal.inicializar();
        asdrubal.nascer();
        asdrubal.alteraIdade();
        System.out.println(asdrubal.idade);
        System.out.println(asdrubal.inicial);
    }

}
