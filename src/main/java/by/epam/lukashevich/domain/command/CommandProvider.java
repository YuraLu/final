package by.epam.lukashevich.domain.command;

import by.epam.lukashevich.domain.command.impl.*;
import by.epam.lukashevich.domain.command.impl.question.*;
import by.epam.lukashevich.domain.command.impl.subject.CommandAddSubject;
import by.epam.lukashevich.domain.command.impl.subject.CommandDeleteSubject;
import by.epam.lukashevich.domain.command.impl.subject.CommandViewSubjectAdd;
import by.epam.lukashevich.domain.command.impl.subject.CommandViewSubjectTable;
import by.epam.lukashevich.domain.command.impl.test.*;
import by.epam.lukashevich.domain.command.impl.user.*;

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
        commands.put(CommandName.VIEW_TEST_ADD_PAGE, new CommandViewTestAdd());
        commands.put(CommandName.VIEW_TEST_WORK_PAGE, new CommandViewTestWorkPage());
        commands.put(CommandName.ADD_TEST, new CommandAddTest());
        commands.put(CommandName.EDIT_TEST, new CommandEditTest());
        commands.put(CommandName.DELETE_TEST, new CommandDeleteTest());


        commands.put(CommandName.VIEW_SUBJECT_TABLE, new CommandViewSubjectTable());
        commands.put(CommandName.VIEW_SUBJECT_ADD_PAGE, new CommandViewSubjectAdd());
        commands.put(CommandName.ADD_SUBJECT, new CommandAddSubject());
        commands.put(CommandName.DELETE_SUBJECT, new CommandDeleteSubject());


        commands.put(CommandName.VIEW_QUESTION_TABLE, new CommandViewQuestionTable());
        commands.put(CommandName.VIEW_QUESTION_WORK_PAGE, new CommandViewQuestionWorkPage());
        commands.put(CommandName.VIEW_QUESTION_ADD_PAGE, new CommandViewQuestionAdd());
        commands.put(CommandName.ADD_QUESTION, new CommandAddQuestion());
        commands.put(CommandName.DELETE_QUESTION, new CommandDeleteQuestion());

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
