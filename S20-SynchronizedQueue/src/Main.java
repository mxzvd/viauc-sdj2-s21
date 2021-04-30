public class Main {
    public static void main(String[] args) {

        Buffer<String> list = new SynchronizedQueue<>(5);

        list.put("gdsfg1");
        list.put("gdssdgfg2");
        list.put("gdsfsaag3");

        System.out.println(list);
    }
}
