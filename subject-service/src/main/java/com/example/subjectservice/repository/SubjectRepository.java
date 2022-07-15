package com.example.subjectservice.repository;

import com.example.subjectservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<List<Subject>> findSubjectByStudentId(Long id);
}
