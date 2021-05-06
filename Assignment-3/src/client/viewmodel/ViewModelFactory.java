package client.viewmodel;

import client.model.Model;

public class ViewModelFactory {

    private Model model;
    private AuthorizationViewModel authorizationViewModel;
    private ApplicationViewModel applicationViewModel;

    public ViewModelFactory(Model model) {
        this.model = model;
        this.authorizationViewModel = new AuthorizationViewModel(model);
        this.applicationViewModel = new ApplicationViewModel(model);
    }

    public AuthorizationViewModel getAuthorizationViewModel() {
        return authorizationViewModel;
    }

    public ApplicationViewModel getApplicationViewModel() {
        return applicationViewModel;
    }
}
