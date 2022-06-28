package valdezet.amigoscodespringboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        this.ensureUniqueEmail(student);
        studentRepository.save(student);
    }

    /* validation methods */

    /**
     * checks a new Student's (not yet saved) email
     * if it already exists in the database.
     *
     * @param student
     * the student to be added to database (via POST request)
     *
     * @throws IllegalStateException
     * simple exception thrown for the sake of the course.
     */
    private void ensureUniqueEmail(Student student) throws IllegalStateException {
        Optional<Student> queriedStudents = studentRepository.findStudentByEmail(student.getEmail());
        if(queriedStudents.isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }
    }

}
