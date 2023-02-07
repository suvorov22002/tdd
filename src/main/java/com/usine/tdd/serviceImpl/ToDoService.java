package com.usine.tdd.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.usine.tdd.models.ToDo;
import com.usine.tdd.repositories.ToDoRepository;
import com.usine.tdd.service.IToDoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ToDoService implements IToDoService{
	
	private final ToDoRepository toDoRepository;
	
	@Override
	public List<ToDo> findAll() {
		//return toDoRepository.findAll();
		return new ArrayList<>();
	}

	@Override
	public ToDo save(ToDo todo) {
		return toDoRepository.save(todo);
	}
	
	
	
	
}
