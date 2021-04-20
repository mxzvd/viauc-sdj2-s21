public class Main {
    public static void main(String[] args) {

        Phone phone = new Phone();

        phone.clickSoundButton(); // Sound
        phone.receive("Test Message");
        System.out.println();

        phone.clickSoundButton(); // Vibration
        phone.receive("Test Message");
        System.out.println();

        phone.clickSoundButton(); // Silent
        phone.receive("Test Message");
        System.out.println();

        phone.clickSoundButton(); // Sound
        phone.receive("Test Message");
        System.out.println();
    }
}
