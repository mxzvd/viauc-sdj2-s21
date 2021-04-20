package model;

import utility.NamedPropertyChangeSubject;

public interface AuctionModel extends NamedPropertyChangeSubject {
    boolean placeBid(int bid, String bidder);
    String getItem();
    int getCurrentBid();
    String getCurrentBidder();
    int getRemainingTimeInSeconds();
}
