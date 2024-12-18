package com.example.demo.service;

import com.example.demo.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRpository extends JpaRepository<Courses,Long> {

}
