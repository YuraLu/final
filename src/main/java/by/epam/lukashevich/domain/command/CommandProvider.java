package by.epam.lukashevich.domain.command;

import by.epam.lukashevich.domain.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> commands = new HashMap<>();


    private CommandProvider() {
        commands.put(CommandName.MISSING, new CommandMissing());
        commands.put(CommandName.SIGN_IN, new CommandSignIn());
        commands.put(CommandName.SIGN_UP, new CommandSignUp());
        commands.put(CommandName.SIGN_OUT, new CommandSignOut());
        commands.put(CommandName.VIEW_SIGN_UP, new CommandViewSignUp());
        commands.put(CommandName.VIEW_USER_CABINET, new CommandViewUserCabinet());
        commands.put(CommandName.VIEW_USER_TABLE, new CommandViewUserTable());
        commands.put(CommandName.CHANGE_USER_STATUS, new CommandChangeUserStatus());
        commands.put(CommandName.CHANGE_USER_BAN_STATUS, new CommandChangeUserBanStatus());
        commands.put(CommandName.VIEW_INDEX, new CommandViewIndex());
        commands.put(CommandName.VIEW_TEST_TABLE, new CommandViewTestTable());
        commands.put(CommandName.VIEW_TEST_EDIT_PAGE, new CommandViewTestEdit());
        commands.put(CommandName.VIEW_TEST_ADD_PAGE, new CommandViewTestAdd());
        commands.put(CommandName.ADD_TEST, new CommandAddTest());
        commands.put(CommandName.EDIT_TEST, new CommandEditTest());
        commands.put(CommandName.DELETE_TEST, new CommandDeleteTest());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name;
        Command command;
        try {
            name = CommandName.fromValue(commandName);
            command = commands.get(name);
        } catch (Exception e) {
            command = commands.get(CommandName.MISSING);
        }
        return command;
    }


}
