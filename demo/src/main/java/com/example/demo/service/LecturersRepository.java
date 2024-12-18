package com.example.demo.service;

import com.example.demo.model.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturersRepository extends JpaRepository<Lecturers,Long> {

}
