public class Main {
    public static void main(String[] args) {

        ThreadSafeLinkedList list = new ThreadSafeLinkedList(2);

        list.add("fsf1");
        list.add("fsfdsf2");
        list.add("fsasdfasdff3");

        System.out.println(list.get(0).toString() + list.get(1).toString() + list.get(2).toString());
    }
}
