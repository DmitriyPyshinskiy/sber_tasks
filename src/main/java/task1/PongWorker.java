package task1;

public class PongWorker extends AbstractWorker implements Runnable {

    public PongWorker(ResourceHolder resourceHolder) {
        super(resourceHolder, "pong", Worker.PONG_WORKER);
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