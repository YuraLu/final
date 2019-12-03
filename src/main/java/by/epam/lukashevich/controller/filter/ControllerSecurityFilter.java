package by.epam.lukashevich.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.domain.entity.user.Role.*;
import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX_PAGE;


@WebFilter(urlPatterns = {"*"}, servletNames = {"controller"})
public class ControllerSecurityFilter implements Filter {

    private List<String> adminActions;
    private List<String> studentActions;
    private List<String> tutorActions;

    @Override
    public void init(FilterConfig filterConfig) {

        adminActions = new ArrayList<>();
        studentActions = new ArrayList<>();
        tutorActions = new ArrayList<>();

        addAdminActions();
        addStudentActions();
        addTutorActions();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession();
        final String command = request.getParameter(PARAMETER_COMMAND);

        if (command != null) {

            int userRoleId = STUDENT.getId();

            if (session.getAttribute(USER_ROLE_ID) != null) {
                userRoleId = (int) (session.getAttribute(USER_ROLE_ID));
            }

            if (userRoleId == ADMIN.getId() && adminActions.contains(command)
                    || userRoleId == TUTOR.getId() && studentActions.contains(command)
                    || userRoleId == STUDENT.getId() && studentActions.contains(command)) {
                request.setAttribute(ALLOWED, true);
            } else {
                request.setAttribute(ALLOWED, false);
                request.setAttribute(REDIRECT_COMMAND, INDEX_PAGE);
            }

            if (!(boolean) request.getAttribute(ALLOWED)) {
                session.setAttribute(SECURITY_MESSAGE, "message.not_allowed");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    private void addStudentActions() {
        studentActions.add(VIEW_INDEX_COMMAND);
        studentActions.add(VIEW_SIGN_IN_PAGE_COMMAND);
        studentActions.add(VIEW_SIGN_UP_PAGE_COMMAND);
        studentActions.add(SIGN_IN_COMMAND);
        studentActions.add(SIGN_UP_COMMAND);
        studentActions.add(SIGN_OUT_COMMAND);
        studentActions.add(UPDATE_PASSWORD_COMMAND);
        studentActions.add(EDIT_USER_COMMAND);
        studentActions.add(VIEW_USER_CABINET_COMMAND);
        studentActions.add(VIEW_TEST_TABLE_COMMAND);
        studentActions.add(VIEW_PASS_TEST_PAGE_COMMAND);
        studentActions.add(VIEW_PASS_TEST_QUESTION_PAGE_COMMAND);
        studentActions.add(FINISH_TEST_COMMAND);
        studentActions.add(VIEW_PASS_TEST_RESULT_PAGE_COMMAND);
        studentActions.add(GET_NEXT_TEST_QUESTION_COMMAND);
        studentActions.add(ABORT_TEST_COMMAND);
    }

    private void addTutorActions() {
        tutorActions.add(VIEW_INDEX_COMMAND);
        tutorActions.add(VIEW_SIGN_IN_PAGE_COMMAND);
        tutorActions.add(VIEW_SIGN_UP_PAGE_COMMAND);
        tutorActions.add(SIGN_IN_COMMAND);
        tutorActions.add(SIGN_UP_COMMAND);
        tutorActions.add(SIGN_OUT_COMMAND);
        tutorActions.add(CHANGE_USER_STATUS_COMMAND);
        tutorActions.add(CHANGE_USER_BAN_STATUS_COMMAND);
        tutorActions.add(UPDATE_PASSWORD_COMMAND);
        tutorActions.add(EDIT_USER_COMMAND);
        tutorActions.add(VIEW_USER_CABINET_COMMAND);
        tutorActions.add(VIEW_USER_TABLE_COMMAND);
        tutorActions.add(VIEW_TEST_TABLE_COMMAND);
        tutorActions.add(VIEW_TEST_ADD_PAGE_COMMAND);
        tutorActions.add(VIEW_TEST_WORK_PAGE_COMMAND);
        tutorActions.add(VIEW_PASS_TEST_PAGE_COMMAND);
        tutorActions.add(VIEW_PASS_TEST_QUESTION_PAGE_COMMAND);
        tutorActions.add(FINISH_TEST_COMMAND);
        tutorActions.add(VIEW_PASS_TEST_RESULT_PAGE_COMMAND);
        tutorActions.add(GET_NEXT_TEST_QUESTION_COMMAND);
        tutorActions.add(ADD_TEST_COMMAND);
        tutorActions.add(DELETE_TEST_COMMAND);
        tutorActions.add(ABORT_TEST_COMMAND);
        tutorActions.add(VIEW_SUBJECT_TABLE_COMMAND);
        tutorActions.add(VIEW_SUBJECT_ADD_PAGE_COMMAND);
        tutorActions.add(ADD_SUBJECT_COMMAND);
        tutorActions.add(DELETE_SUBJECT_COMMAND);
        tutorActions.add(VIEW_QUESTION_TABLE_COMMAND);
        tutorActions.add(VIEW_QUESTION_ADD_PAGE_COMMAND);
        tutorActions.add(ADD_QUESTION_COMMAND);
        tutorActions.add(DELETE_QUESTION_COMMAND);
        tutorActions.add(VIEW_QUESTION_WORK_PAGE_COMMAND);
    }

    private void addAdminActions() {
        adminActions.add(VIEW_INDEX_COMMAND);
        adminActions.add(VIEW_SIGN_IN_PAGE_COMMAND);
        adminActions.add(VIEW_SIGN_UP_PAGE_COMMAND);
        adminActions.add(SIGN_IN_COMMAND);
        adminActions.add(SIGN_UP_COMMAND);
        adminActions.add(SIGN_OUT_COMMAND);
        adminActions.add(CHANGE_USER_STATUS_COMMAND);
        adminActions.add(CHANGE_USER_BAN_STATUS_COMMAND);
        adminActions.add(UPDATE_PASSWORD_COMMAND);
        adminActions.add(EDIT_USER_COMMAND);
        adminActions.add(VIEW_USER_CABINET_COMMAND);
        adminActions.add(VIEW_USER_TABLE_COMMAND);
        adminActions.add(VIEW_TEST_TABLE_COMMAND);
        adminActions.add(VIEW_TEST_ADD_PAGE_COMMAND);
        adminActions.add(VIEW_TEST_WORK_PAGE_COMMAND);
        adminActions.add(VIEW_PASS_TEST_PAGE_COMMAND);
        adminActions.add(VIEW_PASS_TEST_QUESTION_PAGE_COMMAND);
        adminActions.add(FINISH_TEST_COMMAND);
        adminActions.add(VIEW_PASS_TEST_RESULT_PAGE_COMMAND);
        adminActions.add(GET_NEXT_TEST_QUESTION_COMMAND);
        adminActions.add(ADD_TEST_COMMAND);
        adminActions.add(DELETE_TEST_COMMAND);
        adminActions.add(ABORT_TEST_COMMAND);
        adminActions.add(VIEW_SUBJECT_TABLE_COMMAND);
        adminActions.add(VIEW_SUBJECT_ADD_PAGE_COMMAND);
        adminActions.add(ADD_SUBJECT_COMMAND);
        adminActions.add(DELETE_SUBJECT_COMMAND);
        adminActions.add(VIEW_QUESTION_TABLE_COMMAND);
        adminActions.add(VIEW_QUESTION_ADD_PAGE_COMMAND);
        adminActions.add(ADD_QUESTION_COMMAND);
        adminActions.add(DELETE_QUESTION_COMMAND);
        adminActions.add(VIEW_QUESTION_WORK_PAGE_COMMAND);
    }
}