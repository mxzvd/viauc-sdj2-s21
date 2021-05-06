public interface DepositTarget {
    void put(Valuable element);
    Valuable take();
    Valuable look();
    boolean isEmpty();
    boolean isFull();
    int size();
}
