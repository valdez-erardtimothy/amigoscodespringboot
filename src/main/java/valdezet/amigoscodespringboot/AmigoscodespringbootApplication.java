package valdezet.amigoscodespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import valdezet.amigoscodespringboot.student.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class AmigoscodespringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigoscodespringbootApplication.class, args);
	}

	@GetMapping
	public List<Student> hello() {
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
