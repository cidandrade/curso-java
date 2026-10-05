package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

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
                Mensagem.mensagem("Hello, " + this.alguem);
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
                Mensagem.mensagem("Salut, " + this.alguem);
            }
        };
        cumprimentoEmIngles.cumprimentar();
        cumprimentoEmFrances.cumprimentarAlguem("Pierre");
    }
}
