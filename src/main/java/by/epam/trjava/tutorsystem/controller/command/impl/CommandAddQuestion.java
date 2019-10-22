//package by.epam.trjava.tutorsystem.controller.command.impl;
//
//import by.epam.trjava.tutorsystem.controller.command.Command;
//import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
//import by.epam.trjava.tutorsystem.entity.Test;
//import by.epam.trjava.tutorsystem.service.ServiceProvider;
//import by.epam.trjava.tutorsystem.service.TestService;
//import by.epam.trjava.tutorsystem.service.exception.ServiceException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;
//
//
//public class CommandAddQuestion implements Command {
//
//    private static final String TARGET_PAGE = "pages?command=showAllTests";
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String title = request.getParameter(TEST_TITLE);
//        String description = (request.getParameter(TEST_DESCRIPTION) != null)
//                ? request.getParameter(TEST_DESCRIPTION) : "";
//        int subjectId = Integer.parseInt(request.getParameter(TEST_SUBJECT));
//        int authorId = Integer.parseInt(request.getParameter(TEST_AUTHOR));
//
//        HttpSession session = request.getSession();
//        String url = CreatorFullURL.create(request);
//
//        Test test = null;
//        if (!title.isEmpty()) {
//            try {
//                test = new Test();
//                test.setTitle(title);
//                test.setDescription(description);
//                test.setSubjectId(subjectId);
//                test.setAuthorId(authorId);
//
//            } catch (NumberFormatException e) {
//                throw new ServletException("Wrong params data to create test in execute() CommandAddTest", e);
//            }
//        }
//
//        TestService service = ServiceProvider.getInstance().getTestService();
//        try {
//            service.addTest(test);
//        } catch (ServiceException e) {
//            response.sendError(ERROR_NUMBER_500);
//            throw new ServletException("Can't add Test in execute() CommandAddTest", e);
//        }
//        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
//        response.sendRedirect(TARGET_PAGE);
//    }
//}
