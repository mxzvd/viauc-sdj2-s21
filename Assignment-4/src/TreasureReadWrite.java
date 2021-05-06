public interface TreasureReadWrite extends TreasureRead {
    void add(Valuable v);
    Valuable retrieve();
}
