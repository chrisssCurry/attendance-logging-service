package com.example.attendanceloggingservice.mapper;

import com.example.attendanceloggingservice.entity.Student;
import com.example.attendanceloggingservice.model.input.StudentInput;
import com.example.attendanceloggingservice.model.output.StudentOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {

    /*@Mapping(target="employeeId", source = "entity.id")
    @Mapping(target="employeeName", source = "entity.name")
    @Mapping(target="employeeStartDt", source = "entity.startDt",
            dateFormat = "dd-MM-yyyy HH:mm:ss")*/
    StudentOutput studentToStudentOutput(Student entity);

    /*@Mapping(target="id", source="dto.employeeId")
    @Mapping(target="name", source="dto.employeeName")
    @Mapping(target="startDt", source="dto.employeeStartDt",
            dateFormat="dd-MM-yyyy HH:mm:ss")*/
    Student studentInputToStudent(StudentInput dto);
}
