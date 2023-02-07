package com.usine.tdd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usine.tdd.models.ToDo;

@Repository("todoRepo")
public interface ToDoRepository extends JpaRepository<ToDo, Long>{
	
	public ToDo save(ToDo todo);
}
