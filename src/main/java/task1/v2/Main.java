package task1.v2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Resource res = new Resource();
    private static final Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) throws InterruptedException {
        Thread pingThread = new Thread(new PingPongThread(res, "ping", semaphore), "PING");
        Thread pongThread = new Thread(new PingPongThread(res, "pong", semaphore), "PONG");
        pingThread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        pongThread.start();
        doPrint();
    }

    private static void doPrint() {
        while(true) {
            try {
                semaphore.acquire();
                if(PingPongManager.isGameOver()) break;
                if("print".equals(PingPongManager.getNextThread())) {
                    TimeUnit.MILLISECONDS.sleep(700);
                    String value = res.getRes();
                    System.out.println(value);
                    if(value.equals("ping")) PingPongManager.setNextThread("pong");
                    if(value.equals("pong")) PingPongManager.setNextThread("ping");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
