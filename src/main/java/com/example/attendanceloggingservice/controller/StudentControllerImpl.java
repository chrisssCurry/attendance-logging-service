package com.example.attendanceloggingservice.controller;

import com.example.attendanceloggingservice.entity.Student;
import com.example.attendanceloggingservice.model.output.StudentOutput;
import com.example.attendanceloggingservice.service.StudentService;
import com.example.attendanceloggingservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.attendanceloggingservice.util.Constants.DEFAULT_PAGE_SIZE;


@RestController
@RequestMapping(value = "/students")
public class StudentControllerImpl {

    @Autowired
    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentControllerImpl.class);

    private final StudentService studentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<StudentOutput>> getStudentsByMajor(@RequestParam(value = "major") String majorName) {
        Page<StudentOutput> students = studentService.fetchStudentsByMajor(majorName, DEFAULT_PAGE_SIZE);
        logger.info("Found {} total students for the given Major name: '{}', returning {} students...",
                students.getTotalElements(), majorName, DEFAULT_PAGE_SIZE);
        return ResponseEntity.ok(students);
    }
}
