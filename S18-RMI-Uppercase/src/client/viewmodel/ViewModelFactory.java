package client.viewmodel;

import client.model.Model;

public class ViewModelFactory {

    private ConvertViewModel convertViewModel;

    public ViewModelFactory(Model model) {
        convertViewModel = new ConvertViewModel(model);
    }

    public ConvertViewModel getConvertViewModel() {
        return convertViewModel;
    }
}
