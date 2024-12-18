package com.example.demo.controller;

import com.example.demo.model.Lecturers;
import com.example.demo.service.LecturersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/lectures")
@RestController
@CrossOrigin
public class LecturersController {
    private LecturersRepository lecturersRepository;

    public LecturersController(LecturersRepository lecturersRepository) {
        this.lecturersRepository = lecturersRepository;
    }

    //Get----מקבלת קוד מחזירה אובייקט
    @GetMapping(value = "/getLectur/{id}")
    public ResponseEntity<Lecturers> getLectur(@PathVariable Long id){
        Lecturers s=lecturersRepository.getById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    //Get--מחזירה רשימה של אובייקטים
    @GetMapping("/getLectures")
    public ResponseEntity<List<Lecturers>> getLectures(){
        return new ResponseEntity<>(lecturersRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping ("/updateLecture/{id}")
    public ResponseEntity updateLecture(@PathVariable Long id,@RequestBody Lecturers lecturers ){
        if(id!=lecturers.getId())
            return new ResponseEntity(HttpStatus.CONFLICT);
        lecturersRepository.save(lecturers);
        return new ResponseEntity(HttpStatus.OK);
    }

    //delete--מחיקת אובייקט מהטבלה
    @DeleteMapping("/deleteLectur/{id}")
    public ResponseEntity<Void> deleteLectur(@PathVariable Long id){
        lecturersRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // מחזיר 204
    }


    //Post--הוספה של אובייקט לטבלה
    @PostMapping("/addLecturers")
    public ResponseEntity<Lecturers> addLecturers(@RequestBody Lecturers lecturers){
        Lecturers newlecturers=lecturersRepository.save(lecturers);
        return new ResponseEntity<>(newlecturers,HttpStatus.CREATED);
    }
}
