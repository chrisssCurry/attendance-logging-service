package com.example.attendance.repository;

import com.example.attendance.model.Major;
import com.example.attendance.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAllByMajor(Major major, Pageable pageable);

    Optional<Student> findByCardId(String cardId);

    //without the @Query, SpringDataJPA considers "cardId" as the 'id' field, because it's annotated with @Id in Student
    @Query(nativeQuery = true, value = "SELECT * FROM STUDENTS s WHERE s.id = :id")
    Optional<Student> findById(Long id);
}
