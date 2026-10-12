package studentactions;

public class StudentBean {
    private String name = "Student";
    private String course = "Java";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        if (course != null && !course.isEmpty()) {
            this.course = course;
        }
    }
}
