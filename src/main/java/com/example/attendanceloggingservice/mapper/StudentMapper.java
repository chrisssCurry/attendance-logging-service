package com.example.attendanceloggingservice.mapper;

import com.example.attendanceloggingservice.entity.Student;
import com.example.attendanceloggingservice.model.input.StudentInput;
import com.example.attendanceloggingservice.model.output.StudentOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target="id", source = "entity.id")
    @Mapping(target="cardId", source = "entity.cardId")
    @Mapping(target="firstName", source = "entity.firstName")
    @Mapping(target="lastName", source = "entity.lastName")
    @Mapping(target="email", source = "entity.email")
    @Mapping(target="phoneNumbers", source = "entity.phoneNumbers")
    @Mapping(target="major", source = "entity.major")
    StudentOutput studentToStudentOutput(Student entity);

    @Mapping(target="cardId", source="input.cardId")
    @Mapping(target="firstName", source="input.firstName")
    @Mapping(target="lastName", source="input.lastName")
    @Mapping(target="email", source="input.email")
    @Mapping(target="phoneNumbers", source="input.phoneNumbers")
    @Mapping(target="major", source="input.major")
    Student studentInputToStudent(StudentInput input);
}
