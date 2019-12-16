package by.epam.lukashevich.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * This class sets encoding for pages in UTF-8.
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @since JDK1.0
 */

public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}