package org.jhotdraw.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LogManagerTest {
    private LogManager logManager;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        logManager = new LogManager(LogManagerTest.class.getName());
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testLogManagerInitialization() {
        assertNotNull(logManager);
    }

    @Test
    public void testLogManagerLogging() {
        logManager.log(Level.INFO, "Test log message");
        String consoleOutput = outContent.toString().trim();

        assertTrue(consoleOutput.contains("Test log message"));
        assertTrue(consoleOutput.contains(LogManagerTest.class.getName())); // Logger name should be present
    }

    @Test
    public void testLogManagerExceptionHandling() {
        // Create a LogManager with a known logger name to trigger an IOException in LoggerFileHandler
        LogManager faultyLogManager = new LogManager("FaultyLogManager");
        String consoleOutput = outContent.toString().trim();

        assertTrue(consoleOutput.contains("java.io.IOException")); // Exception message should be logged
        assertTrue(consoleOutput.contains("FaultyLogManager")); // Logger name should be present
    }

    @Test
    public void testLogManagerLogLevel() {
        logManager.log(Level.FINE, "Fine-grained log message");
        String consoleOutput = outContent.toString().trim();

        assertTrue(consoleOutput.contains("Fine-grained log message"));
        assertTrue(consoleOutput.contains(LogManagerTest.class.getName()));
    }
}
