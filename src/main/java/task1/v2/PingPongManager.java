package task1.v2;

public class PingPongManager {
    private static final int maxSteps = 12;
    private static int counter;
    private static String nextThread = "ping";

    public static String getNextThread() {
        return nextThread;
    }

    public static void setNextThread(String nextThread) {
        PingPongManager.nextThread = nextThread;
        counter++;
    }

    public static boolean isGameOver() {
        return counter > maxSteps;
    }
}