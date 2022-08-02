package com.example.studentservice.feignclients;

import com.example.studentservice.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "subject-service", url = "http://localhost:8080/subject")
public interface SubjectFeignClient {

    @GetMapping("/byStudent/{id}")
    List<Subject> getSubjectByStudent(@PathVariable Long id);
}
