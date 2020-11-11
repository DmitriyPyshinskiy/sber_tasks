package task1.v2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Resource res = new Resource();
    private static final Semaphore semaphore = new Semaphore(1, true);
    private static final ExecutorService service = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        service.submit(new PingPongThread(res, "ping", semaphore), "PING");
        service.submit(new PingPongThread(res, "pong", semaphore), "PONG");
        doPrint();
    }

    private static void doPrint() {
        while(true) {
            try {
                semaphore.acquire();
                if(PingPongManager.isGameOver()) {
                    service.shutdown();
                    break;
                }
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
