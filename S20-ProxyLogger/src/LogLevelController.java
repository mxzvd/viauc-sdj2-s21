public class LogLevelController implements Logger {

    private String logLevel;
    private ConsoleLogger logger;

    public LogLevelController(String logLevel) {
        this.logLevel = logLevel;
        logger = new ConsoleLogger();
    }

    @Override
    public void log(String txt) {
        switch (logLevel) {
            case "verbose" : {
                logger.log(txt);
                break;
            }
            case "sparse" : {
                if (txt.toLowerCase().contains("error")) logger.log(txt);
            }
        }
    }
}
