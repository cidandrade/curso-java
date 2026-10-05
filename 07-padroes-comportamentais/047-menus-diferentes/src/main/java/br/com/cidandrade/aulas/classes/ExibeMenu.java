package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Mensagem;

public class ExibeMenu {

    private final HotHouse hotdog = new HotHouse();
    private final Redondas pizza = new Redondas();

    public void exibeMenus() {
        StringBuilder sb = new StringBuilder();
        ItemDeMenu item;
        while (hotdog.hasNext()) {
            item = hotdog.next();
            sb.append(item).append("\n");
        }
        Mensagem.mensagem(sb);
        sb.delete(0, sb.length());
        while (pizza.hasNext()) {
            item = pizza.next();
            sb.append(item).append("\n");
        }
        Mensagem.mensagem(sb);
    }

}
