package br.com.cidandrade.aulas;

/**
 * Demonstração de Thread
 *
 * @author cidandrade
 */
public class ThreadApp {

    static int i = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();
    }

    private static void conteMe(String thread) {
        System.out.println("Contador " + ++i + ", atualizado por " + thread);
    }
    private static final Runnable thread1 = () -> {
        for (int x = 0; x < 15; x++) {
            conteMe("Thread 1");
        }
    };
    private static final Runnable thread2 = () -> {
        for (int y = 0; y < 15; y++) {
            conteMe("Thread 2");
        }
    };
    private static final Runnable thread3 = () -> {
        Thread.yield();
        for (int y = 0; y < 15; y++) {
            conteMe("Thread 3");
        }
    };
}
