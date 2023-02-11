package com.example.attendanceloggingservice.controller;

import com.example.attendanceloggingservice.entity.Student;
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


@RestController
@RequestMapping(value = "/students")
public class StudentControllerImpl {

    private final Logger logger = LoggerFactory.getLogger(StudentControllerImpl.class);

    private final StudentService studentService;

    @Autowired
    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<Student>> getStudentsByMajor(@RequestParam(value = "major") String majorName) {
        Page<Student> students = studentService.fetchStudentsByMajor(majorName, Constants.DEFAULT_PAGE_SIZE);
        return ResponseEntity.ok(students);
    }
}
