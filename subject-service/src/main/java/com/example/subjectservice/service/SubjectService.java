package com.example.subjectservice.service;

import com.example.subjectservice.entity.Subject;
import com.example.subjectservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjetsList(){
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id).orElseThrow(()->new NoSuchElementException(("Subject not found")));
    }

    public List<Subject> getSubjectByStudent(Long id){
        return subjectRepository.findSubjectByStudentId(id).orElseThrow(()->new NoSuchElementException("Student not found"));
    }
}
