package com.example.attendance.service;

import com.example.attendance.model.output.StudentOutput;
import org.springframework.data.domain.Page;

public interface StudentService {

    StudentOutput getStudentById(Long id);

    StudentOutput getStudentByCardId(String cardId);

    Page<StudentOutput> getStudentsByMajor(String majorName, Integer pageSize);
}
