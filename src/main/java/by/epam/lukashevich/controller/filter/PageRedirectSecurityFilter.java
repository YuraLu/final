package by.epam.lukashevich.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX_PAGE;

@WebFilter(urlPatterns = {
        "/signIn",
        "/signUp",
        "/usersTable",
        "/userCabinet",
        "/testsTable",
        "/testWorkPage",
        "/subjectAddPage",
        "/subjectsTable",
        "/questionWorkPage",
        "/questionsTable",
        "/passTestPage",
        "/passTestQuestionPage",
        "/passTestResultPage"})
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