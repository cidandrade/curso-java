/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package br.com.cidandrade.aulas;

/**
 *
 * @author cidandrade
 */
public class PojoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Triangulo triangulo = new Triangulo(4.0, 5.0);
        Retangulo retangulo = new Retangulo(4.0, 5.0);
        System.out.println(triangulo.getDescricao());
        System.out.println(retangulo.getDescricao());
    }

}
