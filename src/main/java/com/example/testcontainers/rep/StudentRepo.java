package com.example.testcontainers.rep;

import com.example.testcontainers.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
