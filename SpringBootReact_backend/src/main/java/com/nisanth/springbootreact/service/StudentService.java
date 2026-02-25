package com.nisanth.springbootreact.service;


import com.nisanth.springbootreact.exception.StudentAleadyExistsException;
import com.nisanth.springbootreact.exception.StudentNotFoundException;
import com.nisanth.springbootreact.model.Student;
import com.nisanth.springbootreact.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService
{
    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if (studentAleadyExists(student.getEmail()))
        {
            throw new StudentAleadyExistsException(student.getEmail()+" already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateSTudent(Student student, Long id)
    {
       return studentRepository.findById(id).map(st->{
           st.setFirstName(student.getFirstName());
           st.setLastName(student.getLastName());
           st.setEmail(student.getEmail());
           st.setDepartment(student.getDepartment());
           return studentRepository.save(st);
       }).orElseThrow(()->new StudentNotFoundException("sorry,this student could not be found"));
    }



    @Override
    public Student getStudentById(Long id)
    {

        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("sorry,student not found by this id:"+id));

    }

    @Override
    public void deleteStudent(Long id)
    {
        if(!studentRepository.existsById(id))
        {
            // if student not found
            throw new StudentNotFoundException("sorry,student found");
        }
        studentRepository.deleteById(id);

    }

    private boolean studentAleadyExists(String email)
    {
        return studentRepository.findByEmail(email).isPresent();
    }
}
