package com.example.amigoscodecourse.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student kamil = new Student(
                    "Kamil",
                    "kamilpietrak123@gmail.com",
                    LocalDate.of(2000, 04, 07)
            );
            Student alex = new Student(
                    "Alex",
                    "alexpietrak123@gmail.com",
                    LocalDate.of(2000, 04, 07)
            );

            repository.saveAll(List.of(kamil, alex));
        };
    }
}
