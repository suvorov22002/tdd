package com.usine.tdd.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usine.tdd.models.ToDo;
import com.usine.tdd.serviceImpl.ToDoService;

@RestController
public class ToDoController {
	
	@Autowired
	private ToDoService toDoService;
	
	@GetMapping("/todos")
	ResponseEntity<List<ToDo>> getAllTodos() {
		
		return new ResponseEntity<>(toDoService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/todos")
	ResponseEntity<ToDo> create(@RequestBody ToDo toDo) {
		return new ResponseEntity<>(toDoService.save(toDo), HttpStatus.CREATED);
	}
}
