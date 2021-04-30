import utility.collection.CircularLinkedList;
import utility.collection.DoubleLinkedList;
import utility.collection.LinearLinkedList;
import utility.collection.ListADT;

public class ThreadSafeLinkedList<T> implements ListADT<T> {

    private ListADT<T> list;
    public final static int LINEAR_LINKED_LIST = 1;
    public final static int CIRCULAR_LINKED_LIST = 2;
    public final static int DOUBLE_LINKED_LIST = 3;

    public ThreadSafeLinkedList(int type) {
        switch (type) {
            case 1 : {
                list = new LinearLinkedList<>();
                break;
            }
            case 2 : {
                list = new CircularLinkedList<>();
                break;
            }
            case 3 : {
                list = new DoubleLinkedList<>();
            }
        }
    }

    @Override
    public synchronized void add(int index, T element) {
        list.add(index, element);
    }

    @Override
    public synchronized void add(T element) {
        list.add(element);
    }

    @Override
    public synchronized void set(int index, T element) {
        list.set(index, element);
    }

    @Override
    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized T remove(int index) {
        return list.remove(index);
    }

    @Override
    public synchronized T remove(T element) {
        return list.remove(element);
    }

    @Override
    public synchronized int indexOf(T element) {
        return list.indexOf(element);
    }

    @Override
    public synchronized boolean contains(T element) {
        return list.contains(element);
    }

    @Override
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public synchronized boolean isFull() {
        return list.isFull();
    }

    @Override
    public synchronized int size() {
        return list.size();
    }
}
