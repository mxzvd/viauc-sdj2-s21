public class TestLog {
    public static void main(String[] args) {

        Logger logger1 = new LogLevelController("sparse");
        Logger logger2 = new LogLevelController("verbose");

        logger1.log("log message 1");
        logger1.log("log error message 2");
        logger1.log("log messerrorage 3");

        logger2.log("log message 1");
        logger2.log("log error message 2");
        logger2.log("log messerrorage 3");
    }
}
