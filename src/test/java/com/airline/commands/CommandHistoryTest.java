package com.airline.commands;

import org.junit.Test;

public class CommandHistoryTest {
    @Test
    public void testIsEmpty() {
        CommandHistory history = new CommandHistory();
        assert(history.isEmpty());
    }

    @Test
    public void testPush() {
        CommandHistory history = new CommandHistory();
        history.push(new AddPlaneCommand(null));
        assert(!history.isEmpty());
    }

    @Test
    public void testPop() {
        CommandHistory history = new CommandHistory();
        history.push(new AddPlaneCommand(null));
        history.pop();
        assert(history.isEmpty());
    }
}
