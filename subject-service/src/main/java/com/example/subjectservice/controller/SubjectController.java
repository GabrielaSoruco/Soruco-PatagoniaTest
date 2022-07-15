package com.example.subjectservice.controller;

import com.example.subjectservice.entity.Subject;
import com.example.subjectservice.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjectList(){
        return ResponseEntity.ok(subjectService.getSubjetsList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id){
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @GetMapping("/byStudent/{id}")
    public ResponseEntity<List<Subject>> getSubjectByStudent(@PathVariable Long id){
        return ResponseEntity.ok(subjectService.getSubjectByStudent(id));
    }
}
