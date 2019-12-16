package by.epam.lukashevich.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPPage.*;

/**
 * Filters requests according to the below logic
 * and redirects to page according the command.
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @since JDK1.0
 */
@WebFilter(urlPatterns = {
        SLASH + SIGN_IN_PAGE,
        SLASH + SIGN_UP_PAGE,
        SLASH + USER_TABLE_PAGE,
        SLASH + USER_CABINET_PAGE,
        SLASH + TEST_TABLE_PAGE,
        SLASH + TEST_WORK_PAGE,
        SLASH + PASS_TEST_PAGE,
        SLASH + PASS_TEST_QUESTION_PAGE,
        SLASH + PASS_TEST_RESULT_PAGE,
        SLASH + SUBJECT_TABLE_PAGE,
        SLASH + SUBJECT_ADD_PAGE,
        SLASH + QUESTION_TABLE_PAGE,
        SLASH + QUESTION_WORK_PAGE})
public class PageRedirectSecurityFilter implements Filter {

    private static final String PATH_TO_CONTROLLER_WITH_COMMAND = "/controller?command=";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();

        if (session.getAttribute(USER_ROLE_ID) == null
                && session.getAttribute(MESSAGE_TO_JSP) == null) {
            response.sendRedirect(INDEX_PAGE);
        } else if (session.getAttribute(USER_ROLE_ID) != null
                && session.getAttribute(MESSAGE_TO_JSP) == null) {

            final String redirectTo = request.getContextPath() +
                    PATH_TO_CONTROLLER_WITH_COMMAND + session.getAttribute(REDIRECT_COMMAND);
            response.sendRedirect(redirectTo);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}