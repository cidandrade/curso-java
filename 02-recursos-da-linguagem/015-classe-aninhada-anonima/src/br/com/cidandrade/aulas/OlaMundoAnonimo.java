package br.com.cidandrade.aulas;

public class OlaMundoAnonimo {

    interface OlaMundo {

        public void cumprimentar();

        public void cumprimentarAlguem(String alguem);
    }

    public void digaOla() {
        class CumprimentoEmIngles implements OlaMundo {

            String alguem = "world";

            @Override
            public void cumprimentar() {
                cumprimentarAlguem("world");
            }

            @Override
            public void cumprimentarAlguem(String alguem) {
                this.alguem = alguem;
                System.out.println("Hello, " + this.alguem);
            }

        }
        OlaMundo cumprimentoEmIngles = new CumprimentoEmIngles();
        OlaMundo cumprimentoEmFrances = new OlaMundo() {
            String alguem = "tout le monde";

            @Override
            public void cumprimentar() {
                cumprimentarAlguem("tout le monde");
            }

            @Override
            public void cumprimentarAlguem(String alguem) {
                this.alguem = alguem;
                System.out.println("Salut, " + this.alguem);
            }
        };
        cumprimentoEmIngles.cumprimentar();
        cumprimentoEmFrances.cumprimentarAlguem("Pierre");
    }
}
