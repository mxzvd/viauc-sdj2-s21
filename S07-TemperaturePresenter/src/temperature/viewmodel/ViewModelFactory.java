package temperature.viewmodel;

import temperature.mediator.TemperatureModel;

public class ViewModelFactory {

    private TemperatureViewModel temperatureViewModel;

    public ViewModelFactory(TemperatureModel model) {
        temperatureViewModel = new TemperatureViewModel(model);
    }

    public TemperatureViewModel getTemperatureViewModel() {
        return temperatureViewModel;
    }
}
