package com.example.attendance.service;

import com.example.attendance.model.output.StudentOutput;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service(value = "studentServiceGraphQL")
public class StudentServiceGraphQL implements StudentService {

    @Override
    public StudentOutput getStudentById(Long id) {
        return null;
    }

    @Override
    public StudentOutput getStudentByCardId(String cardId) {
        return null;
    }

    @Override
    public Page<StudentOutput> getStudentsByMajor(String majorName,
                                                  Integer pageSize) {
        return null;
    }
}
