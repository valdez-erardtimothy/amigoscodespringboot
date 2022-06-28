package valdezet.amigoscodespringboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
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

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw produceNotExistsExceptionObject(studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(
            Long studentId,
            String newName,
            String newEmail
    ) {

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> produceNotExistsExceptionObject(studentId));
        if (!newEmail.equals(existingStudent.getEmail())) {
            ensureUniqueEmail(existingStudent);
        }
        existingStudent.setName(newName);
        existingStudent.setEmail(newEmail);

    }

    /* validation methods */

    /**
     * checks a new Student's (not yet saved) email
     * if it already exists in the database.
     *
     * @param student the student to be added to database (via POST request)
     * @throws IllegalStateException simple exception thrown for the sake of the course.
     */
    private void ensureUniqueEmail(Student student) throws IllegalStateException {
        Optional<Student> queriedStudents = studentRepository.findStudentByEmail(student.getEmail());
        if (queriedStudents.isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }
    }

    private static NoSuchElementException produceNotExistsExceptionObject(Long studentId) {
        return new NoSuchElementException(String.format(
                "Student with id %d does not exist",
                studentId
        ));
    }

}
