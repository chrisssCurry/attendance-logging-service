package com.example.attendanceloggingservice.service;

import com.example.attendanceloggingservice.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface StudentService {

    Page<Student> fetchStudentsByMajor(String majorName, Integer pageSize);
}
