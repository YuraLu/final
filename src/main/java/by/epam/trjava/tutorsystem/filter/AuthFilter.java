//package by.epam.trjava.tutorsystem.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.USER_ID;
//
//@WebFilter(urlPatterns = {"/*"})
//public class AuthFilter implements Filter {
//    private FilterConfig filterConfig;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterConfig = filterConfig;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession();
//
//        String uriPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());      // Получаем путь до страницы типа /index.jsp или /login.jsp
//        System.out.println("context path: " + httpRequest.getContextPath() + " path: " + uriPath);
//
//        if (session == null || session.getAttribute(USER_ID) == null) {              // если сессия не создана
//            // надо получить текущий url, чтобы по нему перенаправить потом с login.jsp
//            HttpServletResponse httpResponse = (HttpServletResponse) response;  // Мы не можем вызвать response.sendRedirect("login.jsp") так как нам нужен httpResponse, а не ServletResponse.
//
//            if ("showAllTests.jsp".equals(uriPath) || "index.jsp".equals(uriPath) || "/".equals(uriPath)) {
//                chain.doFilter(request, response);  // вызываем следующий фильтр.
//            }
//            // fixme надо добавить destination
//            httpResponse.sendRedirect("showAllTests.jsp");
//            return;
//        }
//        chain.doFilter(request, response);  // вызываем следующий фильтр.
//    }
//
//    @Override
//    public void destroy() {
//        this.filterConfig = null;
//    }
//}