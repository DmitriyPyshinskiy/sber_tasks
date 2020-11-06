package task1;

public class App {
    public static final int maxSteps = 6;
    public static final ResourceHolder resourceHolder = new ResourceHolder(maxSteps, Worker.PING_WORKER);

    public static void main(String[] args) throws InterruptedException {
        startGame();
        doPrint();
    }

    private static void startGame() {
        Thread pingThread = new Thread(new PingWorker(resourceHolder));
        Thread pongThread = new Thread(new PongWorker(resourceHolder));
        pingThread.start();
        pongThread.start();
    }

    private static void doPrint() throws InterruptedException {
        while(true) {
            synchronized(resourceHolder) {
                if(!Worker.PRINT_WORKER.equals(resourceHolder.getNextWorker())) {
                    resourceHolder.wait();
                }
                if(Worker.PRINT_WORKER.equals(resourceHolder.getNextWorker())) {
                    if(resourceHolder.getCounter() >= maxSteps) {
                        System.out.println(resourceHolder.getResource());
                        System.exit(0);
                    }
                    String resource = resourceHolder.getResource();
                    System.out.println(resource);
                    if("ping".equals(resource)) {
                        resourceHolder.setNextWorker(Worker.PONG_WORKER);
                    }
                    else {
                        resourceHolder.setNextWorker(Worker.PING_WORKER);
                    }
                    resourceHolder.notifyAll();
                }
            }
        }
    }
}
