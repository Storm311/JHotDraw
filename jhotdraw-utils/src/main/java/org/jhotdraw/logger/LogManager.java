package org.jhotdraw.logger;
import java.io.IOException;
import java.util.logging.*;
public class LogManager {
    private final Logger logger;

    public LogManager(String caller) {
        logger = Logger.getLogger(caller);
        logger.setLevel(Level.ALL);

        try {
            this.logger.addHandler(new LoggerFileHandler());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString()); // Handle the exception appropriately in your actual code
        }
    }

    public void log(Level level, String message) {
        logger.log(level, logger.getName() + message);
    }
}
