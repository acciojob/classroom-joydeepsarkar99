package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentHashMap = new HashMap<>();
    HashMap<String,Teacher> teacherHashMap = new HashMap<>();
    HashMap<Teacher, List<Student>> teacherStudentPairHashMap = new HashMap<>();

    public void addStudentToDB(Student student){
        String key = student.getName();
        studentHashMap.put(key,student);
    }

    public void addTeacherToDB(Teacher teacher){
        String key = teacher.getName();
        teacherHashMap.put(key,teacher);
    }

    public void addStudentTeacherPairToDB(String student, String teacher){
        Teacher obj = teacherHashMap.get(teacher);
        if(teacherStudentPairHashMap.containsKey(obj)){
            List<Student> studentList = teacherStudentPairHashMap.get(obj);
            studentList.add(studentHashMap.get(student));
            teacherStudentPairHashMap.put(obj,studentList);
        }
        else{
            List<Student> studentList = new ArrayList<>();
            studentList.add(studentHashMap.get(student));
            teacherStudentPairHashMap.put(obj,studentList);
        }
    }

    public Student getStudentByNameFromDB(String name){
        return studentHashMap.getOrDefault(name,null);
    }

    public Teacher getTeacherByNameFromDB(String name){
        return teacherHashMap.getOrDefault(name,null);
    }

    public List<Student> getStudentsByTeacherNameFromDB(String teacher){
        Teacher obj = teacherHashMap.get(teacher);
        return teacherStudentPairHashMap.getOrDefault(obj,null);
    }

    public List<Student> getAllStudentsFromDB(){
        List<Student> studentList = new ArrayList<>();
        for(Student obj : studentHashMap.values()){
            studentList.add(obj);
        }
        return studentList;
    }

    public void deleteTeacherByNameFromDB(String name){
        Teacher obj = teacherHashMap.get(name);
        teacherHashMap.remove(name);
        List<Student> studentList = teacherStudentPairHashMap.get(obj);
        for(Student student : studentList){
            studentHashMap.remove(student.getName());
        }
        teacherStudentPairHashMap.remove(obj);
    }

    public void deleteAllTeachersFromDB(){
        teacherHashMap.clear();
    }


}
