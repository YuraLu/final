package by.epam.trjava.tutorsystem.controller.command;

import by.epam.trjava.tutorsystem.controller.command.impl.*;
import by.epam.trjava.tutorsystem.controller.command.impl.gotopage.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> commands = new HashMap<>();


    private CommandProvider() {
        commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPageCommand());
        commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.AUTHORIZATION, new CommandAuthorization());
        commands.put(CommandName.REGISTRATION, new CommandRegistration());
        commands.put(CommandName.ADDNEWTEST, new CommandAddTest());
        commands.put(CommandName.GOTOADDNEWTEST, new GoToAddNewTestPageCommand());
        commands.put(CommandName.SHOWALLTESTS, new CommandShowAllTests());
        commands.put(CommandName.GOTOSHOWALLTESTSPAGECOMMAND, new GoToShowAllTestsPageCommand());
        commands.put(CommandName.TESTINFO, new CommandTestInfo());
        commands.put(CommandName.GOTOSHOWTESTINFOPAGECOMMAND, new GoToShowTestInfoPageCommand());
        commands.put(CommandName.LOGOUT, new CommandLogOut());
        commands.put(CommandName.COMMANDMISSING, new CommandMissing());
        commands.put(CommandName.DELETETEST, new CommandDeleteTest());
        commands.put(CommandName.EDITTEST,new CommandEditTest());

        commands.put(CommandName.EDITUSER, new CommandEditUser());
        commands.put(CommandName.VIEWPERSONALCABINET, new CommandViewPersonalCabinet());
        commands.put(CommandName.GOTOPERSONALCABINET, new GoToPersonalCabinetPageCommand());


        commands.put(CommandName.GOTOADDNEWSUBJECT, new GoToAddNewSubjectPageCommand());
        commands.put(CommandName.ADDNEWSUBJECT, new CommandAddTest());
        commands.put(CommandName.GOTOADDNEWQUESTION, new GoToAddNewQuestionPageCommand());
        commands.put(CommandName.ADDNEWQUESTION, new CommandAddTest());
        commands.put(CommandName.GOTOADDNEWANSWER, new GoToAddNewAnswerPageCommand());
        commands.put(CommandName.ADDNEWANSWER, new CommandAddTest());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name;
        Command command;
        try {
            name = CommandName.valueOf(commandName.toUpperCase());
            command = commands.get(name);
        } catch (Exception e) {
            command = commands.get(CommandName.COMMANDMISSING);
        }
        return command;
    }
}
