package com.example.attendance.controller;

import com.example.attendance.model.output.StudentOutput;
import com.example.attendance.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.attendance.util.Constants.DEFAULT_PAGE_SIZE;


@RequestMapping(value = "/students")
@RestController
public class StudentControllerREST implements StudentController {

    @Autowired
    public StudentControllerREST(@Qualifier(value = "studentServiceRest") StudentService studentService) {
        this.studentService = studentService;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentControllerREST.class);

    private final StudentService studentService;


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<StudentOutput> getStudentById(@PathVariable(value = "id") Long id) {
        StudentOutput student = studentService.getStudentById(id);
        logger.info("Found student: '{} {}' registered with given id: '{}'",
                student.getFirstName(), student.getLastName(), id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<StudentOutput> getStudentByCardId(@RequestParam(value = "card") String cardId) {
        StudentOutput student = studentService.getStudentByCardId(cardId);
        logger.info("Found student: '{} {}' registered with given card id: '{}'",
                student.getFirstName(), student.getLastName(), cardId);
        return ResponseEntity.ok(student);
    }

    @GetMapping(value = "/major/{major}")
    @ResponseBody
    public ResponseEntity<Page<StudentOutput>> getStudentsByMajor(@PathVariable(value = "major") String majorName) {
        Page<StudentOutput> students = studentService.getStudentsByMajor(majorName, DEFAULT_PAGE_SIZE);
        logger.info("Found {} total students for the given Major name: '{}', returning {} students...",
                students.getTotalElements(), majorName, DEFAULT_PAGE_SIZE);
        return ResponseEntity.ok(students);
    }
}
