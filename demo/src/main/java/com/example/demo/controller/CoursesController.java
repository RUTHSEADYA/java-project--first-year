package com.example.demo.controller;

import com.example.demo.model.Courses;
import com.example.demo.service.CoursesRpository;
import com.example.demo.service.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/courses")
@RestController
@CrossOrigin
public class CoursesController {

    private CoursesRpository coursesRpository;

    public CoursesController(CoursesRpository coursesRpository, UsersRepository usersRepository) {
        this.coursesRpository = coursesRpository;
    }


    //VVVVVVVVVVVVVV
    //Get----מקבלת קוד מחזירה אובייקט
    @GetMapping(value = "/getCourse/{id}")
    public ResponseEntity<Courses> getCourse(@PathVariable Long id){
        Courses s=coursesRpository.getById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    //vvvvvvvvvvvvvvvv
    //Get--מחזירה רשימה של אובייקטים
    @GetMapping("/getCourses")
    public ResponseEntity<List<Courses>> getCourses(){
        return new ResponseEntity<>(coursesRpository.findAll(), HttpStatus.OK);
    }

    //VVVVVVVVVVVVVV
    //put---עדכון של ספר קיים
    @PutMapping("/updateCourse/{id}")
    public ResponseEntity updateCourse(@PathVariable Long id,@RequestBody Courses courses ){
        if(id!=courses.getId())
            return new ResponseEntity(HttpStatus.CONFLICT);
        coursesRpository.save(courses);
        return new ResponseEntity(HttpStatus.OK);
    }



    //VVVVVVVVVVVVVV
    //delete--מחיקת אובייקט מהטבלה
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        System.out.println(id);
        coursesRpository.deleteById(id);
        return ResponseEntity.noContent().build();  // מחזיר 204
    }


    //vvvvvvvvvvvvvvv
    //Post--הוספה של אובייקט לטבלה
    @PostMapping("/addCourse")
    public ResponseEntity<Courses> addCourse(@RequestBody Courses courses){
        Courses newCourse=coursesRpository.save(courses);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }


}