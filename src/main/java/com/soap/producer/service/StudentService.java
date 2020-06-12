package com.soap.producer.service;

import com.soap.producer.dto.StudentDto;
import com.soap.producer.entity.Student;

public interface StudentService {

    Student getById(Long id);

    Student save(StudentDto studentDto);

}
