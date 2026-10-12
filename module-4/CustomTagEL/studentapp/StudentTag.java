package studentapp;

import java.io.IOException;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class StudentTag extends TagSupport {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(student.getName() + " studies " + student.getCourse());
        } catch (IOException exception) {
            throw new JspException(exception);
        }
        return SKIP_BODY;
    }
}
