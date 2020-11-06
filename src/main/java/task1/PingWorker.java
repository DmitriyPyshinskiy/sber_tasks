package task1;

public class PingWorker extends AbstractWorker implements Runnable {

    public PingWorker(ResourceHolder resourceHolder) {
        super(resourceHolder, "ping", Worker.PING_WORKER);
    }

    @Override
    public void run() {
        try {
            doSteps();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}