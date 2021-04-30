import utility.collection.QueueADT;

public class Main {
    public static void main(String[] args) {

        QueueADT<String> queue = new QueueListBased<>();

        queue.enqueue("1a");
        queue.enqueue("2b");
        queue.enqueue("3c");

        System.out.println(queue);
    }
}
