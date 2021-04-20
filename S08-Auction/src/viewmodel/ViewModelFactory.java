package viewmodel;

import model.AuctionModel;

public class ViewModelFactory {

    private AuctionItemViewModel auctionItemViewModel;

    public ViewModelFactory(AuctionModel model) {
        auctionItemViewModel = new AuctionItemViewModel(model);
    }

    public AuctionItemViewModel getAuctionItemViewModel() {
        return auctionItemViewModel;
    }
}
