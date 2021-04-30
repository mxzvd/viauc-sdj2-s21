public class ZooVisitor {

    private VisitSeaBear proxyInterface;
    private String personType;

    public ZooVisitor(SeaBear seaBear, String personType) {
        proxyInterface = new SeaBearGuard(seaBear);
        this.personType = personType;
    }

    public void view() {
        proxyInterface.view(personType);
    }

    public void feed() {
        proxyInterface.feed(personType);
    }

    public void pet() {
        proxyInterface.pet(personType);
    }
}
