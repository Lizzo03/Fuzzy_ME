
public class TimeTest {
    public static void main(String[] args) throws InterruptedException {
        int timeToTest = 20;
        long runTime;
        Timer timer = new Timer();
        timer.setFormat(0, Timer.FORMAT.MICRO_SECOND);

        //Session Rules Generation Time
        System.out.println("-----Session Rules Generation Time-----");
        runTime = 0;
        for (int i = 0; i < timeToTest; i++) {
            timer.start(0);

            runTime += timer.stop(0);
        }
        runTime = runTime / timeToTest;
        System.out.println("Run Time = " + runTime + " us");
    }
}
