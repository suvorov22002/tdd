package com.usine.tdd;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usine.tdd.models.ToDo;
import com.usine.tdd.serviceImpl.ToDoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	

	
	@MockBean
	private ToDoService todoService;
	
	@Test
	void getAllTodos() throws Exception {
		List<ToDo> toDoList = new ArrayList<ToDo>();
		toDoList.add(new ToDo(1L, "Eat rice",true));
		toDoList.add(new ToDo(2L, "Sleep Twice",true));
		
		when(todoService.findAll()).thenReturn(toDoList);
		mockMvc.perform(MockMvcRequestBuilders.get("/todos")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}
	
	@Test
	void successFullyCreateAToDo() throws Exception {
		
		// Arrange
		ToDo eatToDo = new ToDo(1L, "Eat Rice", true);
		when(todoService.save(eatToDo)).thenReturn(eatToDo);
		//when(toDoService.save(any(ToDo.class))).thenReturn(eatToDo);
		
		// Act
		ObjectMapper objectMapper = new ObjectMapper();
		String eatToDoJSON = objectMapper.writeValueAsString(eatToDo);
		
		ResultActions result = mockMvc.perform(post("/todos")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(eatToDoJSON)
		);
		
		// Assert
		result.andExpect(status().isCreated())
			.andExpect(jsonPath("$.text").value("Eat Rice"))
			.andExpect(jsonPath("$.completed").value(true));
		
	}
	
}
