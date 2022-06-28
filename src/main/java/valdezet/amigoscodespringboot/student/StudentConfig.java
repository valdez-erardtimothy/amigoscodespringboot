package valdezet.amigoscodespringboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student example = new Student(
                    "Example",
                    "example@example.local",
                    22,
                    LocalDate.of(2000, Month.JANUARY,1)
            );

            Student juan = new Student(
                    "Juan",
                    "Juan@example.local",
                    22, // disregard inaccurate a ge
                    LocalDate.of(2005, Month.JANUARY, 1)
            );

            repository.saveAll(List.of(example, juan));
        };
    }
}
