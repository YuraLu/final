package by.epam.lukashevich.domain.command;

import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.CommandMissing;
import by.epam.lukashevich.domain.command.impl.test.CommandPassTestAbort;
import by.epam.lukashevich.domain.command.impl.user.CommandSignIn;
import org.testng.annotations.Test;

import static by.epam.lukashevich.domain.util.config.JSPActionCommand.*;
import static org.testng.Assert.*;

public class CommandProviderTest {

    private CommandProvider provider = CommandProvider.getInstance();

    @Test(expectedExceptions = CommandException.class)
    public void testCreateCommand_invalidCommand_CommandException() throws CommandException {
        Command command = provider.getCommand("noCommand");

        assertTrue(command instanceof CommandMissing);
    }

    @Test
    public void createCommand_signInCommand_correct() throws CommandException {
        Command command = provider.getCommand(SIGN_IN_COMMAND);

        assertTrue(command instanceof CommandSignIn);
    }

    @Test
    public void createCommand_viewUserCabinet_correct() throws CommandException {
        Command command = provider.getCommand(ABORT_TEST_COMMAND);

        assertTrue(command instanceof CommandPassTestAbort);
    }
}