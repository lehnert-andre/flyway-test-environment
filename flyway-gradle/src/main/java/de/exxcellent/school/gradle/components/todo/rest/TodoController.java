package de.exxcellent.school.gradle.components.todo.rest;

import de.exxcellent.school.gradle.components.todo.core.api.TodoService;
import de.exxcellent.school.gradle.components.todo.core.api.to.TodoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

  private final TodoService todoService;

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }


  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<TodoTO> findAll() {
    return todoService.findAllTodos();
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public TodoTO findById(@PathVariable("id") Long id) {
    return todoService.findTodo(id);
  }
}
