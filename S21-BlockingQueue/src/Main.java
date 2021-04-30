public class Main {
    public static void main(String[] args) {

        Buffer<String> queue = new BlockingQueue<>(5);

        (new Thread(() -> {
            while (true) queue.put("1");
        })).start();
        (new Thread(() -> {
            while (true) queue.put("2");
        })).start();
        (new Thread(() -> {
            while (true) queue.put("3");
        })).start();
        (new Thread(() -> {
            while (true) System.out.println(queue.take());
        })).start();
    }
}
