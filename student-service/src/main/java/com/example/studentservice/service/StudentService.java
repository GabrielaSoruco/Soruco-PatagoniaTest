package com.example.studentservice.service;

import com.example.studentservice.entity.Student;
import com.example.studentservice.feignclients.SubjectFeignClient;
import com.example.studentservice.model.Subject;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final SubjectFeignClient subjectFeignClient;


    public StudentService(StudentRepository studentRepository, SubjectFeignClient subjectFeignClient) {
        this.studentRepository = studentRepository;
        this.subjectFeignClient = subjectFeignClient;
    }

    public List<Student> getStudentsList(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Student not found"));
    }

    public Map<String, Object> getSubjectsByStudent(Long studentId){
    Map<String, Object> subjectByStudent = new HashMap<>();
    Student student = this.getStudentById(studentId);
    List<Subject> subjects = subjectFeignClient.getSubjectByStudent(studentId);
    subjectByStudent.put("Student", student);
    subjectByStudent.put("Subjects", subjects);
    return subjectByStudent;
    }
}
