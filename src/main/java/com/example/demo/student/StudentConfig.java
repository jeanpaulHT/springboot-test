package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student M = new Student("Mariam",
                    "mairam@aa",
                    LocalDate.of(2000, Month.APRIL,5));
            Student A = new Student("AA",
                    "AAA@aa",
                    LocalDate.of(2202, Month.APRIL,5));
            studentRepository.saveAll(
                    List.of(M,A)
            );
        };
    }
}
