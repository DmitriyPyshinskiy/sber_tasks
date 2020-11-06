package task1;

public class AbstractWorker {
    private final ResourceHolder resourceHolder;
    private final String workerValue;
    private final Worker worker;

    public AbstractWorker(ResourceHolder resource, String workerValue, Worker worker) {
        this.resourceHolder = resource;
        this.workerValue = workerValue;
        this.worker = worker;
    }

    protected void doSteps() throws InterruptedException {
        while(true) {
            synchronized(resourceHolder) {
                if(!worker.equals(resourceHolder.getNextWorker())) {
                    resourceHolder.wait();
                }
                if(resourceHolder.getCounter() >= resourceHolder.getMaxSteps()) {
                    resourceHolder.notifyAll();
                    break;
                }
                if(worker.equals(resourceHolder.getNextWorker())) {
                    resourceHolder.setResource(workerValue);
                    resourceHolder.setNextWorker(Worker.PRINT_WORKER);
                    resourceHolder.notifyAll();
                }
            }
        }
    }
}