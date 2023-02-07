package com.usine.tdd.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
	
	@Id
	@GeneratedValue
	private Long id;
	private String text;
	private boolean completed;
	
	public ToDo(String text, boolean completed) {
		super();
		this.text = text;
		this.completed = completed;
	}

}
