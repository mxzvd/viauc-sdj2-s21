package viewmodel;

import model.Model;

public class ViewModelFactory {

    private ConvertViewModel convertViewModel;
    private LogViewModel logViewModel;

    public ViewModelFactory(Model model) {
        convertViewModel = new ConvertViewModel(model);
        logViewModel = new LogViewModel(model);
    }

    public ConvertViewModel getConvertViewModel() {
        return convertViewModel;
    }

    public LogViewModel getLogViewModel() {
        return logViewModel;
    }
}
