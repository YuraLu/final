package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandChangeUserStatus implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandChangeUserStatus.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
