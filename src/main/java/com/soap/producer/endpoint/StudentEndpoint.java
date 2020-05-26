package com.soap.producer.endpoint;

import com.soap.producer.dto.StudentDto;
import com.soap.producer.entity.Student;
import com.soap.producer.mapper.StudentDtoMapper;
import com.soap.producer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import producer.soap.AddStudentRequest;
import producer.soap.AddStudentResponse;
import producer.soap.GetStudentRequest;
import producer.soap.GetStudentResponse;
import producer.soap.ObjectFactory;
import producer.soap.ServiceStatus;
import producer.soap.StudentSoapDto;

@Endpoint
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://soap.producer";
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private StudentService studentService;
    private StudentDtoMapper studentDtoMapper;

    @Autowired
    public StudentEndpoint(StudentService studentService, StudentDtoMapper studentDtoMapper) {
        this.studentService = studentService;
        this.studentDtoMapper = studentDtoMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
        GetStudentResponse response = OBJECT_FACTORY.createGetStudentResponse();
        Long id = request.getId();
        Student student = studentService.getById(id);
        StudentSoapDto studentDto = studentDtoMapper.toStudentSoapDto(student);
        response.setStudent(studentDto);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addStudentRequest")
    @ResponsePayload
    public AddStudentResponse addStudent(@RequestPayload AddStudentRequest request) {
        AddStudentResponse response = OBJECT_FACTORY.createAddStudentResponse();
        StudentDto studentDto = studentDtoMapper.toStudentDtoFromSoapDto(request.getStudent());
        ServiceStatus serviceStatus = new ServiceStatus();

        Student newStudent = studentService.save(studentDto);

        if (newStudent == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Student was not saved");
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Student was saved to database successfully");
        }

        StudentSoapDto student = studentDtoMapper.toStudentSoapDto(newStudent);

        response.setStudent(student);
        response.setServiceStatus(serviceStatus);
        return response;
    }

}
