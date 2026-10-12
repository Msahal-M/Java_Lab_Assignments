public class StudentView {
    public void display(Student student) {
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Course: " + student.getCourse());
    }
}
