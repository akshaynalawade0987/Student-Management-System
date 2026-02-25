package com.nisanth.springbootreact.controller;

import com.nisanth.springbootreact.model.Student;
import com.nisanth.springbootreact.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class StudentController
{
   private final IStudentService studentService;

   @GetMapping
   public ResponseEntity<List<Student>> getStudents()
   {
       return  new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
   }

   @PostMapping
   public Student addStudents(@RequestBody Student student)
   {
        return studentService.addStudent(student);
   }

   @PutMapping("/update/{id}")
   public Student updateStudent(@RequestBody Student student,@PathVariable Long id)
   {
      return studentService.updateSTudent(student,id);
   }


   @DeleteMapping("/delete/{id}")
   public void deleteStudent(@PathVariable Long id)
   {
       studentService.deleteStudent(id);
   }
   @GetMapping("/students/{id}")
   public Student getStudentById(@PathVariable Long id)
   {
       return studentService.getStudentById(id);
   }
}
