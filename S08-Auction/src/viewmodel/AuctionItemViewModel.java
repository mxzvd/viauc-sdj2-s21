package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.AuctionModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuctionItemViewModel implements PropertyChangeListener {

    private AuctionModel model;
    private StringProperty item;
    private StringProperty time;
    private StringProperty bid;
    private StringProperty bidder;
    private IntegerProperty currentBid;
    private StringProperty currentBidder;
    private StringProperty error;
    private BooleanProperty end;
    private StringProperty currentBidTitle;

    public AuctionItemViewModel(AuctionModel model) {
        this.model = model;

        item = new SimpleStringProperty();
        time = new SimpleStringProperty();
        bid = new SimpleStringProperty();
        bidder = new SimpleStringProperty();
        currentBid = new SimpleIntegerProperty();
        currentBidder = new SimpleStringProperty();
        error = new SimpleStringProperty();
        end = new SimpleBooleanProperty();
        currentBidTitle = new SimpleStringProperty();

        model.addListener(null, this);

        currentBidTitle.set("Current bid");
        item.set(model.getItem());
        time.set("00:00:" + model.getRemainingTimeInSeconds());
        currentBid.set(model.getCurrentBid());
        currentBidder.set(model.getCurrentBidder());
    }

    public void clear() {
        item.set("");
        time.set("");
        bid.set("");
        bidder.set("");
        currentBid.set(0);
        currentBidder.set("");
        error.set("");
        end.set(false);
        currentBidTitle.set("");
    }

    public void bid() {
        try {
             if (!model.placeBid(Integer.parseInt(bid.get()), "You")) throw new Exception();
        } catch (Exception e) {
            error.set("Bid was not placed");
        }
        bid.set("");
    }

    public StringProperty getItemProperty() {
        return item;
    }

    public StringProperty getBidProperty() {
        return bid;
    }

    public StringProperty getBidderProperty() {
        return bidder;
    }

    public IntegerProperty getCurrentBidProperty() {
        return currentBid;
    }

    public StringProperty getCurrentBidderProperty() {
        return currentBidder;
    }

    public StringProperty getErrorProperty() {
        return error;
    }

    public StringProperty getTimeProperty() {
        return time;
    }

    public BooleanProperty getEndProperty() {
        return end;
    }

    public StringProperty getCurrentBidTitleProperty() {
        return currentBidTitle;
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
                case "bid" :
                    currentBid.set(model.getCurrentBid());
                    currentBidder.set(model.getCurrentBidder());
                    error.set("");
                    break;
                case "time" :
                    time.set((String) evt.getNewValue());
                    break;
                case "end" :
                    currentBidTitle.set("Final bid");
                    error.set("");
                    end.set(true);
            }
        });
    }
}
