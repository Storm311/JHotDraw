package org.jhotdraw.logger;
import java.io.IOException;
import java.util.logging.*;
public class LogManager {
    private final Logger logger;
    private final Level level = Level.ALL;

    public LogManager(String caller) {
        logger = Logger.getLogger(caller);
        logger.setLevel(level);

        try {
            this.logger.addHandler(new LoggerFileHandler());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public Level getLevel() {
        return level;
    }

    public void log(Level level, String message) {
        logger.log(level, logger.getName() + message);
    }
}
