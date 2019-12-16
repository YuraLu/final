package by.epam.lukashevich.presentation.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Date info tag. Represents current date on page.
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @since JDK1.0
 */
public class DateInfoTag extends TagSupport {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public int doStartTag() throws JspException {

        final Date date = new Date();
        final String dateNow = dateFormat.format(date);

        try {
            JspWriter out = pageContext.getOut();
            out.write(dateNow);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}