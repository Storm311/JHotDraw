package org.jhotdraw.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class LoggerFileHandler extends FileHandler {
    public LoggerFileHandler() throws IOException, SecurityException {
        super(createLogFilePath());
        this.setLevel(Level.ALL);
        this.setFormatter(new SimpleFormatter());
    }

    private static String createLogFilePath() throws IOException {
        String logFolderPath = System.getProperty("user.dir") + "/logs/";
        createDirectoryIfNotExists(logFolderPath);

        return logFolderPath + "log_" + getCurrentDate() + "_%g.log";
    }

    private static void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    private static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return currentDate.format(formatter);
    }
}
