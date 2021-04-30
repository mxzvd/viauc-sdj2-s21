import utility.collection.CircularLinkedList;
import utility.collection.ListADT;
import utility.collection.QueueADT;

public class QueueListBased<T> implements QueueADT<T> {

    private ListADT<T> list;

    public QueueListBased() {
        list = new CircularLinkedList<>();
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            toReturn.append(list.get(i).toString());
        }
        return toReturn.toString();
    }

    @Override
    public void enqueue(T element) {
        list.add(list.size(), element);
    }

    @Override
    public T dequeue() {
        return list.remove(0);
    }

    @Override
    public T first() {
        return list.get(0);
    }

    @Override
    public int indexOf(T element) {
        return list.indexOf(element);
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

    @Override
    public int capacity() {
        return -1;
    }
}
