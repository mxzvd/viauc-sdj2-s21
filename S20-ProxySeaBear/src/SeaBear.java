public class SeaBear implements VisitSeaBear {

    @Override
    public void view(String personType) {
        System.out.println("Bear is being viewed by " + personType);
    }

    @Override
    public void feed(String personType) {
        System.out.println("Bear is being fed by " + personType);
    }

    @Override
    public void pet(String personType) {
        System.out.println("Bear is being pet by " + personType);
    }
}
