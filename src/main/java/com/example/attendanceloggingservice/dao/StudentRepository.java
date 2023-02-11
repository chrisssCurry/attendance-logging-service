package com.example.attendanceloggingservice.dao;

import com.example.attendanceloggingservice.model.Major;
import com.example.attendanceloggingservice.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAllByMajor(Major major, Pageable pageable);

    Optional<Student> findByCardId(String cardId);
}
