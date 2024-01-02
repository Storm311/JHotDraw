package org.jhotdraw.logger;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class LoggerFileHandlerTest {
    @Test
    public void testCreateLogFilePath() throws IOException {
        String logFilePath = LoggerFileHandler.createLogFilePath();
        assertTrue(logFilePath.startsWith(System.getProperty("user.dir") + "/logs/"));
        assertTrue(logFilePath.endsWith("_%g.log"));
    }

    @Test
    public void testCreateDirectoryIfNotExists() throws IOException {
        String testDirectory = System.getProperty("user.dir") + "/testlogs/";
        LoggerFileHandler.createDirectoryIfNotExists(testDirectory);
        Path path = Paths.get(testDirectory);
        assertTrue(Files.exists(path) && Files.isDirectory(path));
        Files.deleteIfExists(path);
    }

    @Test
    public void testConstructor() throws IOException {
        LoggerFileHandler fileHandler = new LoggerFileHandler();
        assertSame(Level.ALL, fileHandler.getLevel());
        assertTrue(fileHandler.getFormatter() instanceof SimpleFormatter);
    }
}
