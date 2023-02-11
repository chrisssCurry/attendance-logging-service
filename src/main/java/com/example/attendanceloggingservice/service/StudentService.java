package com.example.attendanceloggingservice.service;

import com.example.attendanceloggingservice.model.output.StudentOutput;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface StudentService {

    Page<StudentOutput> fetchStudentsByMajor(String majorName, Integer pageSize);
}
