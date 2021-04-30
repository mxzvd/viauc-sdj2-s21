import utility.collection.BoundedArrayQueue;
import utility.collection.QueueADT;

public class SynchronizedQueue<T> implements Buffer<T> {

    private QueueADT<T> list;

    public SynchronizedQueue(int capacity) {
        list = new BoundedArrayQueue<>(capacity);
    }

    @Override
    public String toString() {
        return list.toString();
    }


    @Override
    public void put(T element) {
        list.enqueue(element);
    }

    @Override
    public T take() {
        return list.dequeue();
    }

    @Override
    public T look() {
        return list.first();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.isFull();
    }

    @Override
    public int size() {
        return list.size();
    }
}
