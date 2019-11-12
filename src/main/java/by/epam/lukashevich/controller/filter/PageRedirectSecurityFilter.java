package by.epam.lukashevich.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.USER_ROLE_ID;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX;

@WebFilter(urlPatterns = {"/index", "/signUp", "/usersTable", "/testsTable", "/testWorkPage"
        , "/subjectAddPage", "/subjectsTable",  "/questionWorkPage", "/questionsTable"})
public class PageRedirectSecurityFilter implements Filter {

    private static final String PATH_TO_CONTROLLER_WITH_ACTION = "/controller?command=";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        HttpSession session = request.getSession();

        System.out.println(session.getAttribute(REDIRECT_COMMAND));

        String page = (String) session.getAttribute(REDIRECT_COMMAND);
        System.out.println("redirect path " + page);


        if (session.getAttribute(USER_ROLE_ID) == null && !path.endsWith(INDEX)) {
            System.out.println("redirect to login page, cause role id == null");
            response.sendRedirect(INDEX);
        } else if (session.getAttribute(USER_ROLE_ID) != null
                && !path.endsWith(INDEX)) {

            System.out.println("role not null redirect to " + page);

            String redirectTo = request.getContextPath() +
                    PATH_TO_CONTROLLER_WITH_ACTION + session.getAttribute(REDIRECT_COMMAND);
            response.sendRedirect(redirectTo);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}