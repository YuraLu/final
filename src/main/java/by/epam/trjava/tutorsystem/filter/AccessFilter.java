//package by.epam.trjava.tutorsystem.filter;
//
//import by.epam.trjava.tutorsystem.entity.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;
//
//@WebFilter(urlPatterns = {"/*"})
//public class AccessFilter implements Filter {
//
//    private static final String ADD_NEW_TEST = "addNewTest";
//    private static final String TEST_INFO = "testInfo";
//    private static final String TEST_DELETE = "deleteTest";
//    private static final String GO_TO_ADD_NEW_TEST = "goToAddNewTest";
//    private static final String USER_ROLE_STUDENT = "STUDENT";
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        String parameter = servletRequest.getParameter(PARAMETER_COMMAND);
//        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
//        User user = (User) session.getAttribute(USER_OBJECT);
//        String roleName = (String) session.getAttribute(USER_ROLE);
//
//        if (parameter != null && roleName != null) {
//
//            if ((parameter.equals(ADD_NEW_TEST) || parameter.equals(TEST_INFO) || parameter.equals(TEST_DELETE) ||
//                    parameter.equals(GO_TO_ADD_NEW_TEST)) && roleName.equalsIgnoreCase(USER_ROLE_STUDENT)) {
//
//                ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER_403);
//                return;
//            }
//        } else if (parameter != null && roleName == null) {
//
//            if (parameter.equals(ADD_NEW_TEST) ||
//                    parameter.equals(GO_TO_ADD_NEW_TEST)) {
//
//                ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER_403);
//                return;
//            }
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
