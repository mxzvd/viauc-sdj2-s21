package model;

import utility.NamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuctionItem implements NamedPropertyChangeSubject, PropertyChangeListener {
    private String item;
    private Bid currentBid;
    private Timer timer;
    private PropertyChangeSupport property;

    public AuctionItem(String item, int timerSeconds) {
        property = new PropertyChangeSupport(this);
        this.item = item;
        this.timer = new Timer(timerSeconds);
        this.timer.addListener("end", this);
        Thread t = new Thread(timer);
        t.start();
    }

    public synchronized boolean placeBid(int bid, String bidder) {
        Bid oldBid = currentBid;
        if (getTimerSeconds() <= 0) {
            return false;
        } else if (bidder != null && bidder.equals(getCurrentBidder())) {
            return false;
        } else if (currentBid != null && bid <= currentBid.getBid()) {
            return false;
        }
        currentBid = new Bid(bid, bidder);
        property.firePropertyChange("bid", oldBid, currentBid);
        return true;
    }

    public synchronized String getItem() {
        return item;
    }

    public synchronized String getCurrentBidder() {
        return currentBid == null ? "No bidders" : currentBid.getBidder();
    }

    public synchronized int getCurrentBid() {
        return currentBid == null ? 0 : currentBid.getBid();
    }

    public synchronized int getTimerSeconds() {
        return timer == null ? 0 : timer.getTimerSeconds();
    }

    @Override
    synchronized public void addListener(String propertyName,
                                         PropertyChangeListener listener) {
        if (propertyName == null) {
            property.addPropertyChangeListener(listener);
            timer.addListener("time", listener);
        } else if (propertyName.equals("time")) {
            timer.addListener(propertyName, listener);
        } else {
            property.addPropertyChangeListener(propertyName, listener);
        }
    }

    @Override
    public synchronized void removeListener(String propertyName,
                                            PropertyChangeListener listener) {
        if (propertyName == null) {
            property.removePropertyChangeListener(listener);
            timer.removeListener("time", listener);
        } else if (propertyName.equals("time")) {
            timer.removeListener(propertyName, listener);
        } else {
            property.removePropertyChangeListener(propertyName, listener);
        }
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        property.firePropertyChange(evt.getPropertyName(), null, currentBid);
    }
}
