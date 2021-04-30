public class Main {
    public static void main(String[] args) {

        PublicToilet toilet = new ToiletBuilding();

        Thread[] people = new Thread[6];
        for (int i = 0; i < people.length; i++) {
            (people[i] = new Thread(new Person(toilet), "Person " + i)).start();
        }

        (new Thread(new CleaningPerson(toilet), "Janitor")).start();
    }
}
