import model.Model;
import model.ModelManager;
import view.SimpleConsoleView;

public class StudentListMain {
    public static void main(String args[]) {
        Model model = new ModelManager();

        // simple console view
        SimpleConsoleView view = new SimpleConsoleView(model);
    }
}
