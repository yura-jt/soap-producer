package com.soap.producer.service.impl;

import com.soap.producer.dto.StudentDto;
import com.soap.producer.entity.Student;
import com.soap.producer.exception.EntityNotFoundException;
import com.soap.producer.mapper.StudentDtoMapper;
import com.soap.producer.repository.StudentRepository;
import com.soap.producer.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentDtoMapper studentDtoMapper;

    @Override
    public Student getById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            String message = String.format("Student with id = %s was not found", id);
            log.warn(message);
            throw new EntityNotFoundException(message);
        }
        return optionalStudent.get();
    }

    @Override
    public Student save(StudentDto studentDto) {
        Student student = studentDtoMapper.toStudent(studentDto);
        return studentRepository.save(student);
    }

}
