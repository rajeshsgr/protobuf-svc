package com.raj.nola.protobuf.web.rest;

import com.raj.nola.protobuf.model.StudentProto.Student;
import com.raj.nola.protobuf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentSvc;


    @RequestMapping("/students")
    Student listAllStudents() {
        return studentSvc.getAllStudent();
    }
}
