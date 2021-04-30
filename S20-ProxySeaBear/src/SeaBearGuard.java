public class SeaBearGuard implements VisitSeaBear {

    private SeaBear seaBear;

    public SeaBearGuard(SeaBear seaBear) {
        this.seaBear = seaBear;
    }

    @Override
    public void view(String personType) {
        seaBear.view(personType);
    }

    @Override
    public void feed(String personType) {
        if (personType.equalsIgnoreCase("zookeeper")) {
            seaBear.feed(personType);
        } else {
            System.out.println(personType + " is not allowed to feed the sea bear.");
        }
    }

    @Override
    public void pet(String personType) {
        if (personType.equalsIgnoreCase("child")) {
            seaBear.pet(personType);
        } else {
            System.out.println(personType + " is not allowed to pet the sea bear.");
        }
    }
}
