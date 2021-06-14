public class Main {
    public static void main(String[] args) {

        HeavyWeightList list = new HeavyWeightList(3, 5);
        ReadWriteAccess sharedResource = new ListAccess(list);

        Thread[] readers = new Thread[25];
        for (int i = 0; i < readers.length; i++) {
            (readers[i] = new Thread(new Reader(sharedResource), "Reader " + i)).start();
        }

        (new Thread(new Writer(sharedResource), "Writer 1")).start();
        (new Thread(new Writer(sharedResource), "Writer 2")).start();
    }
}
