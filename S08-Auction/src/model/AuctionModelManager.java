package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuctionModelManager implements AuctionModel {
    private AuctionItem auctionItem;
    private PropertyChangeSupport property;

    public AuctionModelManager() {
        this.auctionItem = new AuctionItem("iPhone 7", 40);
        new AuctionHouseSimulator(this);
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public boolean placeBid(int bid, String bidder) {
        return auctionItem.placeBid(bid, bidder);
    }

    @Override
    public String getItem() {
        return auctionItem.getItem();
    }

    @Override
    public int getCurrentBid() {
        return auctionItem.getCurrentBid();
    }

    @Override
    public String getCurrentBidder() {
        return auctionItem.getCurrentBidder();
    }

    @Override
    public int getRemainingTimeInSeconds() {
        return auctionItem.getTimerSeconds();
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        auctionItem.addListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        auctionItem.removeListener(propertyName, listener);
    }
}
