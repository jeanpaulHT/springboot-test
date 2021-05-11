package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
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


    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void  addNewStudent(Student student){
        Optional<Student> studentOptional =  studentRepository
                .findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            try {
                throw new IllegalAccessException("an student alredy has this email:" + student.getEmail());
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else{
            studentRepository.save(student);
        }

    }

    public void deleteStuden(Long id) {
        boolean exist = studentRepository.existsById(id);

        if(!exist){
            try {
                throw new IllegalAccessException("there is no student with the id: " + id);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else{
            studentRepository.deleteById(id);
        }

    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if(studentOptional.isEmpty()){
            try {
                throw new IllegalAccessException("there is no student with the id: " + id);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else{
            Student student = studentOptional.get();
            System.out.println(student.getEmail() + " " + student.getName());
            System.out.println(name + " " + email);
            student.setName(name);
            student.setEmail(email);
        }

    }
}
