public class Main {
    public static void main(String[] args) {

        Car car = new Car();

        for (int i = 0; i < 45; i++) {
            car.buttonClick();
            System.out.println(car.status());
        }
    }
}
