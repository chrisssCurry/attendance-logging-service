package com.example.attendance.service;

import com.example.attendance.repository.StudentRepository;
import com.example.attendance.exception.ApiRequestException;
import com.example.attendance.mapper.StudentMapper;
import com.example.attendance.model.Major;
import com.example.attendance.model.output.StudentOutput;
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

@Service(value = "studentServiceRest")
public class StudentServiceREST implements StudentService {

    @Autowired
    public StudentServiceREST(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentServiceREST.class);

    private static final String AVAILABLE_MAJORS = Arrays.toString(Major.values());

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;


    @Override
    public StudentOutput getStudentById(Long id) {
        logger.info("Searching for student by given id: '{}'", id);
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentOutput)
                .orElseThrow(() -> {
                    logger.error("Cannot find any student registered with given 'id': '{}'", id);
                    throw new ApiRequestException(
                            HttpStatusCode.valueOf(404),
                            "Cannot find any student registered with given card id: " + id
                    );
                });
    }

    @Override
    public StudentOutput getStudentByCardId(String cardId) {
        logger.info("Searching for student by given card id: '{}'", cardId);
        return studentRepository.findByCardId(cardId)
                .map(studentMapper::studentToStudentOutput)
                .orElseThrow(() -> {
                    logger.error("Cannot find any student registered with given card 'id': '{}'", cardId);
                    throw new ApiRequestException(
                            HttpStatusCode.valueOf(404),
                            "Cannot find any student registered with given card id: " + cardId
                    );
                });
    }

    @Override
    public Page<StudentOutput> getStudentsByMajor(String majorName,
                                                  Integer pageSize) {
        logger.info("Fetching all students for the given Major name: '{}' - page size: {}", majorName, pageSize);

        if (isValidMajor(majorName)) {
            Major major = Major.getMajor(majorName);
            Pageable studentPage = PageRequest.of(0, pageSize);
            return studentRepository.findAllByMajor(major, studentPage)
                    .map(studentMapper::studentToStudentOutput);
        } else {
            logger.error("An error has occured - received invalid Major name: '{}'", majorName);
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(400);
            String message = String.format(
                    "Received invalid Major name: '%s'. Please use one of the following: %s",
                    majorName,
                    AVAILABLE_MAJORS
            );
            throw new ApiRequestException(httpStatusCode, message);
        }
    }

    private boolean isValidMajor(String majorName) {

        // [a, b, c, ...] -> (a|b|c| ...)
        String availableMajorsRegex =
                AVAILABLE_MAJORS.replaceAll("\\[", "(")
                        .replaceAll(", ", "|")
                        .replaceAll("]", ")");

        Pattern pattern = Pattern.compile(availableMajorsRegex);
        Matcher matcher = pattern.matcher(majorName.toUpperCase());
        return matcher.find();
    }
}
