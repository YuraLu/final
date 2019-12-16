package by.epam.lukashevich.domain.command;

import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.test.CommandPassTestAbort;
import by.epam.lukashevich.domain.command.impl.user.CommandSignIn;
import by.epam.lukashevich.domain.command.provider.CommandProvider;
import org.junit.Test;


import static by.epam.lukashevich.domain.config.JSPActionCommand.*;
import static org.junit.Assert.assertTrue;


public class CommandProviderTest {

    private final CommandProvider provider = CommandProvider.getInstance();

    @Test(expected= CommandException.class)
    public void testCreateCommand_invalidCommand_CommandException() throws CommandException {
        Command command = provider.getCommand("noCommand");
    }

    @Test
    public void createCommand_signInCommand_correct() throws CommandException {
        Command command = provider.getCommand(SIGN_IN_COMMAND);

        assertTrue(command instanceof CommandSignIn);
    }

    @Test
    public void createCommand_abortTestCommand_correct() throws CommandException {
        Command command = provider.getCommand(ABORT_TEST_COMMAND);

        assertTrue(command instanceof CommandPassTestAbort);
    }
}