package com.example.springboot.repository;

import com.example.springboot.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> { }
