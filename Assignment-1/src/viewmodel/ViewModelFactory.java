package viewmodel;

import mediator.TemperatureModel;

// Typical ViewModelFactory class.
public class ViewModelFactory {
    
    private OverviewViewModel overviewViewModel;
    private LogViewModel logViewModel;

    public ViewModelFactory(TemperatureModel model) {
        overviewViewModel = new OverviewViewModel(model);
        logViewModel = new LogViewModel(model);
    }

    public OverviewViewModel getOverviewViewModel() {
        return overviewViewModel;
    }

    public LogViewModel getLogViewModel() {
        return logViewModel;
    }
}
