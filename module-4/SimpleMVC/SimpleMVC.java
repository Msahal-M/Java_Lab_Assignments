/*
Develop a simple Student Management application following Model-View-Controller architecture.
*/
public class SimpleMVC {
    public static void main(String[] args) {
        Student student = new Student(1, "Asha", "Computer Science");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        controller.displayStudent();
    }
}
