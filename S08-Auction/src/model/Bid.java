package model;

public class Bid {
    private int bid;
    private String bidder;

    public Bid(int bid, String bidder) {
        this.bid = bid;
        this.bidder = bidder;
    }

    public int getBid() {
        return bid;
    }

    public String getBidder() {
        return bidder;
    }

    @Override public String toString() {
        return "{" + "bid=" + bid + ", bidder=" + bidder + "}";
    }
}
