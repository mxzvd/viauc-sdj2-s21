public class Main {
    public static void main(String[] args) {

        Door d = new Door();

        System.out.println("Start up, the door is " + d.status()); // Closed

        d.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // Do nothing.
        }
        System.out.println("Clicked, the door is " + d.status()); // Open


        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Stay open


        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Closing

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // Do nothing.
        }
        System.out.println("Clicked, the door is " + d.status()); // Closed

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Opening

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // Do nothing.
        }
        System.out.println("Clicked, the door is " + d.status()); // Open

        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            // Do nothing.
        }

        System.out.println("Clicked, the door is " + d.status()); // Closed
    }
}
