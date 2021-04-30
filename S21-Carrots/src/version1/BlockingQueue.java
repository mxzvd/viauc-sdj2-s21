package version1;

import version1.utility.collection.BoundedArrayQueue;

public class BlockingQueue<T> implements Buffer<T> {

    private BoundedArrayQueue<T> queue;

    public BlockingQueue(int capacity) {
        queue = new BoundedArrayQueue<>(capacity);
    }

    @Override
    public synchronized String toString() {
        return queue.toString();
    }

    @Override
    public synchronized void put(T element) {
        if (element == null) throw new IllegalArgumentException("Argument can't be null.");
        while (queue.isFull()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is now waiting to put an element.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        queue.enqueue(element);
        notifyAll();
        System.out.println("Added " + element + " to the queue. Totaling " + queue.size() + " in the queue.");
    }

    @Override
    public synchronized T take() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is now waiting to take an element.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        T toReturn = queue.dequeue();
        notifyAll();
        System.out.println("Taken an element. Totaling " + queue.size() + " in the queue.");
        return toReturn;
    }

    @Override
    public synchronized T look() {
        return queue.isEmpty() ? null : queue.dequeue();
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized boolean isFull() {
        return queue.isFull();
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }
}
