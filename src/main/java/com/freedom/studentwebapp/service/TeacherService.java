package com.freedom.studentwebapp.service;

import com.freedom.studentwebapp.entity.Teacher;
import com.freedom.studentwebapp.entity.University;
import com.freedom.studentwebapp.repository.TeacherRepository;
import com.freedom.studentwebapp.restcontroller.dto.TeacherDto;

public class TeacherService {
    private TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public TeacherDto add(TeacherDto teacherDto){
        Teacher teacherEntitiy = new Teacher();
        teacherEntitiy.setName(teacherDto.getName());
        teacherEntitiy.setSurname(teacherDto.getSurname());
        teacherEntitiy.setSalary(teacherDto.getSalary());
        teacherEntitiy.setUniversityId(new University(teacherDto.getUniversityId()));

        repository.insert(teacherEntitiy);

        teacherDto.setId(teacherEntitiy.getId());

        return teacherDto;
    }
}
