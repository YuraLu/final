package by.epam.lukashevich.domain.command.impl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.MESSAGE_TO_JSP;

public final class CheckMessage {


    private CheckMessage() {
    }

    //check presence any messages to jsp or not
    // if yes took message from session and put it with rename
    public static void checkMessageToJsp(final HttpSession session,
                                         final HttpServletRequest request,
                                         final String... messageToCopy) {

        for (String messageToLook : messageToCopy) {
            if (session.getAttribute(messageToLook) != null) {
                final String message = String.valueOf(session.getAttribute(messageToLook));
                request.setAttribute(MESSAGE_TO_JSP, message);
                session.removeAttribute(messageToLook);
            }
        }
    }
}