package br.com.cidandrade.aulas.facade;

import br.com.cidandrade.aulas.classes.Catalisador;
import br.com.cidandrade.aulas.classes.Ignicao;
import br.com.cidandrade.aulas.classes.ControladorFluxoDeAr;
import br.com.cidandrade.aulas.classes.InjetorDeCombustivel;
import br.com.cidandrade.aulas.classes.Radiador;

public class CarroFacade {

    private static final int TEMPERATURA_MAXIMA = 90;
    private static final int TEMPERATURA_IDEAL = 50;
    private final InjetorDeCombustivel injecaoDeCombustivel;
    private final ControladorFluxoDeAr controleFluxoAr;
    private final Ignicao ignicao;
    private final Radiador radiador;
    private final Catalisador catalisador;

    public CarroFacade() {
        this.catalisador = new Catalisador();
        this.radiador = new Radiador();
        this.ignicao = new Ignicao();
        this.controleFluxoAr = new ControladorFluxoDeAr();
        this.injecaoDeCombustivel = new InjetorDeCombustivel();
    }

    public void ligarMotor() {
        injecaoDeCombustivel.ligar();
        controleFluxoAr.aspirar();
        injecaoDeCombustivel.ligar();
        injecaoDeCombustivel.injetar();
        ignicao.ligar();
        radiador.setLimiteMaxTemperatura(TEMPERATURA_MAXIMA);
        radiador.acionar();
        catalisador.ligar();
    }

    public void desligarMotor() {
        injecaoDeCombustivel.desligar();
        catalisador.desligar();
        radiador.verificar(TEMPERATURA_IDEAL);
        radiador.parar();
        controleFluxoAr.desligar();
    }
}
