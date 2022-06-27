package valdezet.amigoscodespringboot.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
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
