package version1;

public interface Buffer<T> {
    void put(T element);
    T take();
    T look();
    boolean isEmpty();
    boolean isFull();
    int size();
}
