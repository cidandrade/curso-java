package br.com.cidandrade.aulas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Uma Thread informa à outra que ele deve ser interrompida. Essa informação é
 * passada em uma variável com controle de concorrência, para impedir Race
 * Conditions
 *
 * @author cidandrade
 */
public class ThreadApp {

    static AtomicBoolean interromper = new AtomicBoolean(false);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(thread1).start();
        new Thread(thread2).start();
    }

    private static void msgInterrupcao(String msg, long i) {
        System.out.println("A " + msg + " contou até " + i);
    }

    private static final Runnable thread1 = new Runnable() {
        @Override
        public void run() {
            long i = 0;
            for (; !interromper.get(); i++) {
            }
            msgInterrupcao("Thread 1", i);
        }

    };

    private static final Runnable thread2 = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
            }
            while (!interromper.get()) {
                interromper = new AtomicBoolean(true);
            }
            System.out.println("Acordei");
        }
    };
}
