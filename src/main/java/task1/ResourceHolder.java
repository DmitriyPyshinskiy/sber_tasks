package task1;

public class ResourceHolder {

    private final int maxSteps;
    private String resource;
    private int counter;
    private Worker nextWorker;


    public ResourceHolder(int maxSteps, Worker nextWorker) {
        this.maxSteps = maxSteps;
        this.nextWorker = nextWorker;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String value) {
        this.resource = value;
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public Worker getNextWorker() {
        return nextWorker;
    }

    public void setNextWorker(Worker nextWorker) {
        this.nextWorker = nextWorker;
    }
}