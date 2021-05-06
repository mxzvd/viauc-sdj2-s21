import mediator.MediatorManager;
import mediator.Mediator;
import model.Model;
import model.ModelManager;

public class Main {
    public static void main(String[] args) {
        Model model = new ModelManager();
        Mediator mediator = new MediatorManager(model);
    }
}
