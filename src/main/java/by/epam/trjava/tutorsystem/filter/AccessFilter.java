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
//import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.USER_OBJECT;
//
//@WebFilter(urlPatterns = {"/*"})
//public class AccessFilter implements Filter {
//
//    private static final String ADD_NEW_TEST = "addNewTest";
//    private static final String GO_TO_ADD_NEW_TEST = "goToAddNewTest";
//    private static final int USER_ROLE_STUDENT = 2;
//    private static final String COMMAND = "command";
//    private static final Integer ERROR_NUMBER = 403;
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
//        String parameter = servletRequest.getParameter(COMMAND);
//        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
//        User user = (User) session.getAttribute(USER_OBJECT);
//
//        if (parameter != null && user != null) {
//
//            if ((parameter.equals(ADD_NEW_TEST) ||
//                    parameter.equals(GO_TO_ADD_NEW_TEST)) && user.getRoleId()== USER_ROLE_STUDENT) {
//
//                ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
//                return;
//            }
//        } else if (parameter != null && user == null) {
//
//            if (parameter.equals(ADD_NEW_TEST) ||
//                    parameter.equals(GO_TO_ADD_NEW_TEST)) {
//
//                ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
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
