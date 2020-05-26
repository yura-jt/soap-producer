package com.soap.producer.mapper;

import com.soap.producer.dto.StudentDto;
import com.soap.producer.entity.Student;
import org.mapstruct.Mapper;
import producer.soap.StudentSoapDto;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    StudentDto toStudentDto(Student student);

    Student toStudent(StudentDto studentDto);

    StudentSoapDto toStudentSoapDto(Student student);

    StudentDto toStudentDtoFromSoapDto(StudentSoapDto studentSoapDto);

}
