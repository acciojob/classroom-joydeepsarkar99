package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student){
        studentRepository.addStudentToDB(student);
    }

    public void addTeacher(Teacher teacher){
        studentRepository.addTeacherToDB(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){
        studentRepository.addStudentTeacherPairToDB(student,teacher);
    }

    public Student getStudentByName(String name){
        return studentRepository.getStudentByNameFromDB(name);
    }
    public Teacher getTeacherByName(String name){
        return studentRepository.getTeacherByNameFromDB(name);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        List<Student> studentList = studentRepository.getStudentsByTeacherNameFromDB(teacher);
        List<String> studentNameList = new ArrayList<>();

        for(Student obj : studentList){
            studentNameList.add(obj.getName());
        }
        return studentNameList;
    }

    public List<String> getAllStudents(){
        List<Student> studentList = studentRepository.getAllStudentsFromDB();
        List<String> studentNameList = new ArrayList<>();

        for(Student obj : studentList){
            studentNameList.add(obj.getName());
        }
        return studentNameList;
    }

    public void deleteTeacherByName(String name){
        studentRepository.deleteTeacherByNameFromDB(name);
    }

    public void deleteAllTeachers(){
        studentRepository.deleteAllTeachersFromDB();
    }

}
