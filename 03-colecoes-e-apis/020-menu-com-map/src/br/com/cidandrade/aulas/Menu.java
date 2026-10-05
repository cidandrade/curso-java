package br.com.cidandrade.aulas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {

    Map<String, Prato> menu;

    public void limpar() {
        menu.clear();
    }

    public boolean temPrato(String nome) {
        return menu.containsKey(nome);
    }

    public Prato pratoPorNome(String nome) {
        return menu.get(nome);
    }

    public boolean estaVazio() {
        return menu.isEmpty();
    }

    public void remove(String nome) {
        menu.remove(nome);
    }

    public int qtosPratos() {
        return menu.size();
    }

    public List<String> nomesDosPratos() {
        List<String> nomes = new ArrayList<>();
        menu.forEach((k, v) -> nomes.add(k));
        return nomes;
    }

    public void imprimeNomesDosPratos() {
        nomesDosPratos().forEach(System.out::println);
    }

    public void imprime() {
        menu.forEach((k, v) -> System.out.println(v));
    }

    public void imprimeOrdenado() {
        List<String> nomes = nomesDosPratos();
        Collections.sort(nomes);
        nomes.forEach((p) -> System.out.println(pratoPorNome(p)));
    }

    public void popular() {
        Prato prato;
        prato = new Prato("Brunch Muralha",
                "Café da manhã da Muralha", 5.0F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Torta Baratheon",
                "Torta de carne de javali", 15.0F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Costela Tyrell",
                "Costela de carne de carneiro", 17.0F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Cebolas à Seaworth",
                "Cebolas Grelhadas com molho", 9.0F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Frango do Trono",
                "Frango ao mel", 9.8F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Sopa Sor",
                "Sopa aguada de alho-poró", 6.5F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Torta Arya",
                "Torta de nozes e frutas", 8.0F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Doce Sansa",
                "Pêssego em molho de mel e cominho", 5.8F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Lorde Comandante",
                "Mirtilo com creme", 4.0F);
        menu.put(prato.getNome(), prato);
        prato = new Prato("Peixe Lannister",
                "Truta envolta em bacon", 18.0F);
        menu.put(prato.getNome(), prato);
    }

    public Menu() {
        menu = new HashMap<>();
    }
}
