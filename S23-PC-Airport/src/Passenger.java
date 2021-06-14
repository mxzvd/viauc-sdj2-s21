public class Passenger {

    private String nationality;
    private int passportNumber;

    public Passenger(String nationality, int passportNumber) {
        this.nationality = nationality;
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public String toString() {
        return "{" + nationality + ", " + passportNumber + "}";
    }
}
