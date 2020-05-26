package com.soap.producer.mapper;

import com.soap.producer.dto.StudentDto;
import com.soap.producer.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    StudentDto toStudentDto(Student student);

    Student toStudent(StudentDto studentDto);

}
