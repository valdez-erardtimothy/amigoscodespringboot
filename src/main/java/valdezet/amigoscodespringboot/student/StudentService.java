package valdezet.amigoscodespringboot.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Example",
                        "example@example.local",
                        22,
                        LocalDate.of(2022, Month.JANUARY,1)
                )
        );
    }
}
