package br.com.cidandrade.aulas;

public class Hamburguer {

    private final byte carnes;
    private final TamanhoCarne tamanho;
    private final boolean queijoPrato, queijoBrie, presunto, ovo,
            bacon, alface, rucula, agriao, tomate;

    private Hamburguer(Construtor construtor) {
        super();
        this.carnes = construtor.carnes;
        this.tamanho = construtor.tamanho;
        this.queijoPrato = construtor.queijoPrato;
        this.queijoBrie = construtor.queijoBrie;
        this.presunto = construtor.presunto;
        this.ovo = construtor.ovo;
        this.bacon = construtor.bacon;
        this.alface = construtor.alface;
        this.rucula = construtor.rucula;
        this.agriao = construtor.agriao;
        this.tomate = construtor.tomate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hamburguer");
        switch (carnes) {
            case 2:
                sb.append(" duplo");
                break;
            case 3:
                sb.append(" triplo");
                break;
            case 4:
                sb.append(" quádruplo");
                break;
        }
        sb.append(String.format(" %d gr.", tamanho.getPeso()));
        if (queijoPrato) {
            sb.append(", queijo prato");
        }
        if (queijoBrie) {
            sb.append(", queijo brie");
        }
        if (presunto) {
            sb.append(", presunto");
        }
        if (ovo) {
            sb.append(", ovo");
        }
        if (bacon) {
            sb.append(", bacon");
        }
        if (alface) {
            sb.append(", alface");
        }
        if (rucula) {
            sb.append(", rúcula");
        }
        if (agriao) {
            sb.append(", agrião");
        }
        if (tomate) {
            sb.append(", tomate");
        }
        return sb.toString();
    }

    public static class Construtor {

        //Requerido
        private final byte carnes;
        private final TamanhoCarne tamanho;
        //Opcional
        private boolean queijoPrato = false;
        private boolean queijoBrie = false;
        private boolean presunto = false;
        private boolean ovo = false;
        private boolean bacon = false;
        private boolean alface = false;
        private boolean rucula = false;
        private boolean agriao = false;
        private boolean tomate = false;

        public Construtor(byte carnes, TamanhoCarne tamanho) {
            carnes = (carnes > 0 ? carnes : 1);
            carnes = (carnes < 5 ? carnes : 4);
            this.carnes = carnes;
            this.tamanho = tamanho;
        }

        public Construtor queijoPrato() {
            queijoPrato = true;
            return this;
        }

        public Construtor queijoBrie() {
            queijoBrie = true;
            return this;
        }

        public Construtor presunto() {
            presunto = true;
            return this;
        }

        public Construtor ovo() {
            ovo = true;
            return this;
        }

        public Construtor bacon() {
            bacon = true;
            return this;
        }

        public Construtor alface() {
            alface = true;
            return this;
        }

        public Construtor rucula() {
            rucula = true;
            return this;
        }

        public Construtor agriao() {
            agriao = true;
            return this;
        }

        public Construtor tomate() {
            tomate = true;
            return this;
        }

        public Hamburguer construir() {
            return new Hamburguer(this);
        }
    }

    public enum TamanhoCarne {
        P(100), M(125), G(160);
        private final int peso;

        private TamanhoCarne(int peso) {
            this.peso = peso;
        }

        public int getPeso() {
            return peso;
        }

    }
}
