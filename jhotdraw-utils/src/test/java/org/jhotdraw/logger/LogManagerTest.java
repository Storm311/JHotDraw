package org.jhotdraw.logger;

import org.junit.Before;
import org.junit.Test;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class LogManagerTest {
    private LogManager logManager;

    @Before
    public void setUp() {
        logManager = new LogManager("TestLogger");
    }

    @Test
    public void testGetLogger() {
        Logger logger = logManager.getLogger();
        assertNotNull(logger);
        assertEquals("TestLogger", logger.getName());
    }

    @Test
    public void testGetLevel() {
        Level level = logManager.getLevel();
        assertEquals(Level.ALL, level);
    }
}
