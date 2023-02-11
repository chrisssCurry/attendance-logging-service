package com.example.attendanceloggingservice.service;

import com.example.attendanceloggingservice.dao.StudentRepository;
import com.example.attendanceloggingservice.exception.ApiRequestException;
import com.example.attendanceloggingservice.model.Major;
import com.example.attendanceloggingservice.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private static final String AVAILABLE_MAJORS = Arrays.toString(Major.values());

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Page<Student> fetchStudentsByMajor(String majorName, Integer pageSize) {

        // [a, b, c, ...] -> (a|b|c| ...)
        String availableMajorsRegex =
                AVAILABLE_MAJORS.replace("\\[", "(")
                                .replaceAll(",", "|")
                                .replace("\\]", ")");

        Pattern pattern = Pattern.compile(availableMajorsRegex);
        Matcher matcher = pattern.matcher(majorName);

        if (matcher.find()) {
            Major major = Major.getMajor(majorName);
            Pageable studentPage = PageRequest.of(0, pageSize);
            return studentRepository.findAllByMajor(major, studentPage);
        } else {
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(400);
            String message = String.format(
                    "Received invalid Major name: '%s'. Please use one of the following: %s",
                    majorName,
                    AVAILABLE_MAJORS
            );
            throw new ApiRequestException(httpStatusCode, message);
        }
    }
}
