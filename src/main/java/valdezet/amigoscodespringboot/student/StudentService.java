package valdezet.amigoscodespringboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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

        if (shouldUpdateStringField(newName, existingStudent.getName())) {
            existingStudent.setName(newName);
        }
        if (shouldUpdateStringField(newEmail, existingStudent.getEmail())) {
            if (!newEmail.equals(existingStudent.getEmail())) {
                ensureUniqueEmail(newEmail);
            }
            existingStudent.setEmail(newEmail);
        }

    }

    /* validation methods */

    private static boolean shouldUpdateStringField(@Nullable String newValue, String oldValue) {
        return newValue != null && !newValue.equals(oldValue) && newValue.length() > 0;
    }

    /**
     * overload of below method to check against instantiated email
     *
     * @param student the student to be added to database (via POST request)
     */
    private void ensureUniqueEmail(Student student) throws IllegalStateException {
        ensureUniqueEmail(student.getEmail());
    }

    /**
     * checks a new Student's (not yet saved) email
     * if it already exists in the database.
     *
     * @throws IllegalStateException simple exception thrown for the sake of the course.
     */
    private void ensureUniqueEmail(String email) throws IllegalStateException {
        Optional<Student> queriedStudents = studentRepository.findStudentByEmail((email));
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
