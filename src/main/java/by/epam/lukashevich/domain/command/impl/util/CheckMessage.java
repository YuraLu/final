package by.epam.lukashevich.domain.command.impl.util;

import by.epam.lukashevich.domain.config.BeanFieldJsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.MESSAGE_TO_JSP;

/**
 * Checks presence any message in jsp or not,
 * if yes took message from session and put it back with rename to MESSAGE_TO_JSP
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @see BeanFieldJsp
 * @since JDK1.0
 */
public final class CheckMessage {


    private CheckMessage() {
    }

    public static void checkMessageToJsp(final HttpSession session,
                                         final HttpServletRequest request,
                                         final String... messageToCopy) {

        for (String messageToCheck : messageToCopy) {
            if (session.getAttribute(messageToCheck) != null) {
                final String message = String.valueOf(session.getAttribute(messageToCheck));
                request.setAttribute(MESSAGE_TO_JSP, message);
                session.removeAttribute(messageToCheck);
            }
        }
    }
}