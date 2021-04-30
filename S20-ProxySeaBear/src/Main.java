public class Main {
    public static void main(String[] args) {

        SeaBear seaBear = new SeaBear();
        ZooVisitor child = new ZooVisitor(seaBear, "child");
        ZooVisitor adult = new ZooVisitor(seaBear, "adult");
        ZooVisitor zookeeper = new ZooVisitor(seaBear, "zookeeper");

        child.feed();
        child.pet();
        child.view();
        adult.feed();
        adult.view();
        adult.pet();
        zookeeper.feed();
        zookeeper.pet();
        zookeeper.view();
    }
}
