public class Main {
    public static void main(String[] args){
        System.out.println("solitaire solver webservices is starting");
        long startTime = System.currentTimeMillis();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("System was interrupted");
        }

        System.out.println("System started in \"" + (System.currentTimeMillis() - startTime) + "\" ms");
    }
}
