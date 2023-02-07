package com.usine.tdd;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.usine.tdd.models.ToDo;
import com.usine.tdd.repositories.ToDoRepository;
import com.usine.tdd.serviceImpl.ToDoService;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoServiceTests {
	
//	@MockBean
//	private ToDoRepository toDoRepository;
	
	@Mock ToDoRepository toDoRepository;
	
	
	@AfterEach
	void tearDown(){
	    toDoRepository.deleteAll();
	}
	
	@Test
	void getAllToDosService() {
		
		ToDo toDoSample = new ToDo("Todo Sample 1", true);
		System.out.println("++++++++toDoSample+++++++++++ "+toDoSample);
		ToDo t = toDoRepository.save(toDoSample);
		System.out.println("++++++++++++++++++++ "+t);
		
		
		when(toDoRepository.insert(any(ToDo.class))).then(new Answer<ToDo>() {
	        Long sequence = 1L;
	            
	        @Override
	        public ToDo answer(InvocationOnMock invocation) throws Throwable {
	        	ToDo toDoSample = (ToDo) invocation.getArgument(0);
	        	toDoSample.setId(sequence++);
	            return toDoSample;
	        }
	    });
		
		ToDoService toDoService = new ToDoService(toDoRepository);
		
		List<ToDo> toDoList = toDoService.findAll();
		System.out.println(toDoList.size());
		ToDo lastToDo = toDoList.get(toDoList.size() - 1);
		
		assertEquals(toDoSample.getText(), lastToDo.getText());
		assertEquals(toDoSample.isCompleted(), lastToDo.isCompleted());
		assertEquals(toDoSample.getId(), lastToDo.getId());
		
	}
	
	@Test
	void saveAToDo() {
		
		ToDoService toDoService = new ToDoService(toDoRepository);
		ToDo toDoSample = new ToDo("Todo Sample 1", true);
		
		toDoService.save(toDoSample);
		assertEquals(1.0, toDoRepository.count());
	}
}
