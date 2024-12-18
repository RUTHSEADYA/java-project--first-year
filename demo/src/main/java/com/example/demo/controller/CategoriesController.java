package com.example.demo.controller;

import com.example.demo.model.Categories;
import com.example.demo.service.CategoriesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("api/categories")

public class CategoriesController {


    private CategoriesRepository categoriesRepository;

    //Get----מקבלת קוד מחזירה אובייקט
    @GetMapping(value = "/getCategories/{id}")
    public ResponseEntity<Categories> getCategories(@PathVariable Long id){
        Categories s=categoriesRepository.getById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    //Get--מחזירה רשימה של אובייקטים
    @GetMapping("/getCategories")
    public ResponseEntity<List<Categories>> getCategories(){
        return new ResponseEntity<>(categoriesRepository.findAll(), HttpStatus.OK);
    }

    //put---עדכון של ספר קיים
    @GetMapping("/updateCategory")
    public ResponseEntity updateCategory(@RequestBody Categories categories , @PathVariable Long id){
        if(id!=categories.getId())
            return new ResponseEntity(HttpStatus.CONFLICT);
        categoriesRepository.save(categories);
        return new ResponseEntity(HttpStatus.OK);
    }

    //delete--מחיקת אובייקט מהטבלה
    @DeleteMapping("/deleteCategory")
    public ResponseEntity deleteCategory(@PathVariable Long id){
        categoriesRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //Post--הוספה של אובייקט לטבלה
    @PostMapping("/addCategory")
    public ResponseEntity<Categories> addCategory(Categories categories){
        Categories c=categoriesRepository.save(categories);
        return new ResponseEntity(c,HttpStatus.CREATED);
    }


}
