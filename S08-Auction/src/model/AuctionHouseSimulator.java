package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuctionHouseSimulator implements PropertyChangeListener {
    public AuctionHouseSimulator(AuctionModel model) {
        System.out.println("Auction item: " + model.getItem());

        model.addListener(null, this);

        new Thread(() -> {
            String[] bidders = {"Bob", "Wendy"};
            for (int i = 0; i < 6; i++) {
                model.placeBid(model.getCurrentBid() + 10, bidders[i % 2]);
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("-->" + evt.getPropertyName() + ": " + evt.getNewValue());
    }
}
