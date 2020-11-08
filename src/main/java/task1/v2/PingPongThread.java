package task1.v2;

import java.util.concurrent.Semaphore;

public class PingPongThread implements Runnable {
    private final Semaphore semaphore;
    private final Resource res;
    private final String value;

    public PingPongThread(Resource res, String value, Semaphore semaphore) {
        this.res = res;
        this.value = value;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while(true) {
            try {
                semaphore.acquire();
                if(PingPongManager.isGameOver()) break;
                if(value.equals(PingPongManager.getNextThread())) {
                    res.setRes(value);
                    PingPongManager.setNextThread("print");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
