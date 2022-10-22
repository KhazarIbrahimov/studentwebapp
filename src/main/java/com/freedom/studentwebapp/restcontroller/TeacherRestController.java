package com.freedom.studentwebapp.restcontroller;

import com.freedom.studentwebapp.entity.Teacher;
import com.freedom.studentwebapp.repository.TeacherRepository;
import com.freedom.studentwebapp.restcontroller.dto.ResponseDto;
import com.freedom.studentwebapp.restcontroller.dto.TeacherDto;
import com.freedom.studentwebapp.service.TeacherService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("teachers")
public class TeacherRestController {

    private final TeacherRepository teacherRepository = new TeacherRepository();
    private final TeacherService teacherService= new TeacherService(teacherRepository);

    @GetMapping
    public List<Teacher> getList(@RequestParam(required = false) String name , @RequestParam(required = false) String surname , @RequestParam(required = false) BigDecimal salary){
        return teacherRepository.getList(name,surname,salary);
    }

    @PostMapping
    public ResponseDto<TeacherDto> add(@RequestBody TeacherDto teacher){

        final ResponseDto<TeacherDto> responseDto = new ResponseDto<>();
        responseDto.setData(teacherService.add(teacher));
        responseDto.setMessage("succesfully inserted");

        return responseDto;
    }

    @PutMapping
    public boolean update(@RequestBody Teacher teacher){
        return teacherRepository.update(teacher);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Integer id){
        return teacherRepository.delete(id);
    }

}
