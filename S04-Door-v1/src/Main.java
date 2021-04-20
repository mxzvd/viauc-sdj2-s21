public class Main {
    public static void main(String[] args) {

        Door d = new Door();

        System.out.println("Start up, the door is " + d.status()); // Closed

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Opening

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Closing

        d.complete();
        System.out.println("Completed, the door is " + d.status()); // Closed

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Opening

        d.complete();
        System.out.println("Completed, the door is " + d.status()); // Open

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // StayOpen

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Closing

        d.complete();
        System.out.println("Completed, the door is " + d.status()); // Closed

        d.timeout();
        System.out.println("Timeout? the door is " + d.status()); // Closed

        d.click();
        System.out.println("Clicked, the door is " + d.status()); // Opening

        d.complete();
        System.out.println("Completed, the door is " + d.status()); // Open

        d.timeout();
        System.out.println("Timeout, the door is " + d.status()); // Closing

        d.complete();
        System.out.println("Completed, the door is " + d.status()); // Closed
    }
}
