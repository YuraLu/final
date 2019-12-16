package by.epam.lukashevich.domain.command.provider;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.CommandName;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.CommandViewIndex;
import by.epam.lukashevich.domain.command.impl.question.CommandAddQuestion;
import by.epam.lukashevich.domain.command.impl.question.CommandDeleteQuestion;
import by.epam.lukashevich.domain.command.impl.question.CommandViewQuestionTable;
import by.epam.lukashevich.domain.command.impl.question.CommandViewQuestionWorkPage;
import by.epam.lukashevich.domain.command.impl.subject.CommandAddSubject;
import by.epam.lukashevich.domain.command.impl.subject.CommandDeleteSubject;
import by.epam.lukashevich.domain.command.impl.subject.CommandViewSubjectTable;
import by.epam.lukashevich.domain.command.impl.test.*;
import by.epam.lukashevich.domain.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides Command by given string parameter.
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @since JDK1.0
 */
public final class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        addUserCommands();
        addTestCommands();
        addSubjectCommands();
        addQuestionCommands();
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) throws CommandException {
        return commands.get(CommandName.fromValue(commandName));
    }

    private void addUserCommands() {
        commands.put(CommandName.VIEW_INDEX, new CommandViewIndex());
        commands.put(CommandName.VIEW_SIGN_IN, new CommandViewSignIn());
        commands.put(CommandName.VIEW_SIGN_UP, new CommandViewSignUp());
        commands.put(CommandName.VIEW_USER_CABINET, new CommandViewUserCabinet());
        commands.put(CommandName.VIEW_USER_TABLE, new CommandViewUserTable());
        commands.put(CommandName.CHANGE_USER_STATUS, new CommandChangeUserStatus());
        commands.put(CommandName.CHANGE_USER_BAN_STATUS, new CommandChangeUserBanStatus());
        commands.put(CommandName.SIGN_IN, new CommandSignIn());
        commands.put(CommandName.SIGN_UP, new CommandSignUp());
        commands.put(CommandName.SIGN_OUT, new CommandSignOut());
        commands.put(CommandName.EDIT_USER, new CommandEditUser());
        commands.put(CommandName.UPDATE_PASSWORD, new CommandUpdateUserPassword());
    }

    private void addTestCommands() {
        commands.put(CommandName.VIEW_TEST_TABLE, new CommandViewTestTable());
        commands.put(CommandName.VIEW_TEST_WORK_PAGE, new CommandViewTestWorkPage());
        commands.put(CommandName.VIEW_PASS_TEST_PAGE, new CommandViewPassTestPage());
        commands.put(CommandName.VIEW_PASS_TEST_QUESTION_PAGE, new CommandViewPassTestQuestionPage());
        commands.put(CommandName.VIEW_PASS_TEST_RESULT_PAGE, new CommandViewPassTestResultPage());
        commands.put(CommandName.ADD_TEST, new CommandAddTest());
        commands.put(CommandName.DELETE_TEST, new CommandDeleteTest());
        commands.put(CommandName.ABORT_TEST, new CommandPassTestAbort());
        commands.put(CommandName.FINISH_TEST, new CommandPassTestFinish());
        commands.put(CommandName.GET_NEXT_TEST_QUESTION, new CommandGetNextTestQuestion());
    }

    private void addSubjectCommands() {
        commands.put(CommandName.VIEW_SUBJECT_TABLE, new CommandViewSubjectTable());
        commands.put(CommandName.ADD_SUBJECT, new CommandAddSubject());
        commands.put(CommandName.DELETE_SUBJECT, new CommandDeleteSubject());
    }

    private void addQuestionCommands() {
        commands.put(CommandName.VIEW_QUESTION_TABLE, new CommandViewQuestionTable());
        commands.put(CommandName.VIEW_QUESTION_WORK_PAGE, new CommandViewQuestionWorkPage());
        commands.put(CommandName.ADD_QUESTION, new CommandAddQuestion());
        commands.put(CommandName.DELETE_QUESTION, new CommandDeleteQuestion());
    }
}