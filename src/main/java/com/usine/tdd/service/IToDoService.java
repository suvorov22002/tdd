package com.usine.tdd.service;

import java.util.List;

import com.usine.tdd.models.ToDo;

public interface IToDoService {
	
	public List<ToDo> findAll();
	public ToDo save(ToDo todo);
}
