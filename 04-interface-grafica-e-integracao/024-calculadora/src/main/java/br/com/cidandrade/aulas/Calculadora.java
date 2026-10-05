package br.com.cidandrade.aulas;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calculadora {

    private String display;
    private final Pilha pilha;
    private boolean entrDeInteiro;
    private TipoEntrada tipoEntrada;
    private boolean estadoErro;
    private final int MAX_CHARS = 10;

    public String entrNumerica(int entrada) {
        if (!estadoErro) {
            if (tipoEntrada != TipoEntrada.COMUM) {
                display = "0.";
                if (tipoEntrada == TipoEntrada.NOVA_SOBE) {
                    pilha.sobe();
                }
                tipoEntrada = TipoEntrada.COMUM;
            }
            if (contaAlgarismos(display) < MAX_CHARS) {
                if (entrDeInteiro) {
                    eliminaPontoADireita();
                    limpaSeZero();
                }
                display = incluiPontoADireita(display + entrada);
                pilha.alteraCorrente(display);
            }
        }
        return display;
    }

    public String pontoPress() {
        if (!estadoErro) {
            entrDeInteiro = false;
            if (tipoEntrada != TipoEntrada.COMUM) {
                display = "0.";
            }
        }
        return display;
    }

    public void enterPress() {
        if (!estadoErro) {
            pilha.sobe();
            tipoEntrada = TipoEntrada.NOVA_NAO_SOBE;
            entrDeInteiro = true;
        }
    }

    public String operacao(TipoOperacao operacao) {
        if (!estadoErro) {
            try {
                switch (operacao) {
                    case RAIZ:
                        display = formataDisplay(
                                pilha.unaria(TipoOperacao.RAIZ));
                        break;
                    case SINAL:
                        display = formataDisplay(
                                pilha.unaria(TipoOperacao.SINAL));
                        break;
                    case SOMA:
                        display = formataDisplay(
                                pilha.binaria(TipoOperacao.SOMA));
                        break;
                    case SUBTRACAO:
                        display = formataDisplay(
                                pilha.binaria(
                                        TipoOperacao.SUBTRACAO));
                        break;
                    case MULTIPLICACAO:
                        display = formataDisplay(
                                pilha.binaria(
                                        TipoOperacao.MULTIPLICACAO));
                        break;
                    case DIVISAO:
                        display = formataDisplay(
                                pilha.binaria(
                                        TipoOperacao.DIVISAO));
                        break;
                    case EXPONENCIACAO:
                        display = formataDisplay(pilha.binaria(
                                TipoOperacao.EXPONENCIACAO));
                        break;
                }
                tipoEntrada = TipoEntrada.NOVA_SOBE;
                entrDeInteiro = true;
            } catch (Exception e) {
                disparaErro();
            }
        }
        return display;
    }

    public String raizPress() {
        return operacao(TipoOperacao.RAIZ);
    }

    public String sinalPress() {
        return operacao(TipoOperacao.SINAL);
    }

    public String somaPress() {
        return operacao(TipoOperacao.SOMA);
    }

    public String subtraiPress() {
        return operacao(TipoOperacao.SUBTRACAO);
    }

    public String multPress() {
        return operacao(TipoOperacao.MULTIPLICACAO);
    }

    public String divPress() {
        return operacao(TipoOperacao.DIVISAO);
    }

    public String elevPress() {
        return operacao(TipoOperacao.EXPONENCIACAO);
    }

    public String limpa() {
        display = "0.";
        pilha.limpa();
        entrDeInteiro = true;
        tipoEntrada = TipoEntrada.COMUM;
        estadoErro = false;
        return display;
    }

    private void eliminaPontoADireita() {
        if (display.charAt(display.length() - 1) == '.') {
            display = display.substring(0, display.length() - 1);
        }
    }

    private String incluiPontoADireita(String str) {
        if (!str.contains(".")) {
            str = str + ".";
        }
        return str;
    }

    private void limpaSeZero() {
        if (display.equals("0")) {
            display = "";
        }
    }

    private void disparaErro() {
        estadoErro = true;
        display = "ERROR";
    }

    private String formataDisplay(BigDecimal bDec) {
        String str = bDec.toString();
        if (contaAlgarismos(str) > MAX_CHARS) {
            if (bDec.doubleValue() > Math.pow(10, MAX_CHARS)) {
                disparaErro();
                str = display;
            } else {
                str = str.substring(0, MAX_CHARS + 1);
            }
        }
        while (str.contains(".") && str.endsWith("0")) {
            str = str.substring(0, str.length() - 1);
        }
        str = incluiPontoADireita(str);
        pilha.alteraCorrente(str);
        return str;
    }

    private int contaAlgarismos(String str) {
        return str.replace(".", "").length();

    }

    public enum TipoEntrada {
        COMUM, NOVA_SOBE, NOVA_NAO_SOBE
    }

    public enum TipoOperacao {
        RAIZ, SINAL, SOMA, SUBTRACAO, MULTIPLICACAO, DIVISAO, EXPONENCIACAO
    }

    public Calculadora() {
        display = "0.";
        pilha = new Pilha();
        pilha.limpa();
        entrDeInteiro = true;
        tipoEntrada = TipoEntrada.COMUM;
        estadoErro = false;
    }

    interface OpUnInterf {

        BigDecimal operacao(BigDecimal a);
    }

    interface OpBinInterf {

        BigDecimal operacao(BigDecimal a, BigDecimal b);
    }

    class Pilha {

        private final BigDecimal[] pilha;
        private final int TAM_PILHA = 4;
        private final int POS_CORR = 3;

        private void sobe() {
            for (int i = 0; i < POS_CORR; i++) {
                pilha[i] = pilha[i + 1];
            }
        }

        private void desce() {
            for (int i = POS_CORR; i > 0; i--) {
                pilha[i] = pilha[i - 1];
            }
        }

        public void alteraCorrente(String valor) {
            pilha[POS_CORR] = new BigDecimal(valor);
        }

        private void limpa() {
            for (int i = 0; i < TAM_PILHA; i++) {
                pilha[i] = BigDecimal.ZERO;
            }
        }

        public BigDecimal unaria(TipoOperacao tOperacao) throws Exception {
            OpUnInterf op;
            switch (tOperacao) {
                case RAIZ:
                    op = (a) -> BigDecimal.valueOf(
                            Math.sqrt(a.doubleValue()));
                    break;
                case SINAL:
                    op = (a) -> a.multiply(BigDecimal.valueOf(-1.0));
                    break;
                default:
                    op = (a) -> (a);
            }
            pilha[POS_CORR] = operacaoUnaria(pilha[POS_CORR], op);
            return pilha[POS_CORR];
        }

        public BigDecimal binaria(TipoOperacao tOperacao) throws Exception {
            OpBinInterf op;
            switch (tOperacao) {
                case SOMA:
                    op = (a, b) -> a.add(b);
                    break;
                case SUBTRACAO:
                    op = (a, b) -> a.subtract(b);
                    break;
                case MULTIPLICACAO:
                    op = (a, b) -> a.multiply(b);
                    break;
                case DIVISAO:
                    op = (a, b) -> a.divide(b,
                            MathContext.DECIMAL128);
                    break;
                case EXPONENCIACAO:
                    op = (a, b) -> BigDecimal.valueOf(
                            Math.pow(a.doubleValue(),
                                    b.doubleValue()));
                    break;
                default:
                    op = (a, b) -> a;
                    break;
            }
            pilha[POS_CORR - 1] = operacaoBinaria(pilha[POS_CORR - 1],
                    pilha[POS_CORR], op);
            desce();
            return pilha[POS_CORR];
        }

        private BigDecimal operacaoUnaria(BigDecimal a, OpUnInterf op) {
            return op.operacao(a);
        }

        private BigDecimal operacaoBinaria(BigDecimal a,
                BigDecimal b, OpBinInterf op) {
            return op.operacao(a, b);
        }

        public Pilha() {
            pilha = new BigDecimal[TAM_PILHA];
            limpa();
        }

    }
}
