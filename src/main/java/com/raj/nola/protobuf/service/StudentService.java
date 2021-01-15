package com.raj.nola.protobuf.service;

import com.raj.nola.account.model.AccountProto;
import com.raj.nola.protobuf.model.StudentProto.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentService {


    public Student getAllStudent(){

        Student student= Student.newBuilder()
                .setStudentId(1)
                .setStudentName("Denis")
                .build();

        return student;

    }

}
