package de.exxcellent.school.gradle.components.todo.core.impl.business;

import de.exxcellent.school.gradle.components.todo.core.api.TodoService;
import de.exxcellent.school.gradle.components.todo.core.api.to.TodoTO;
import de.exxcellent.school.gradle.components.todo.core.impl.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoFacade implements TodoService {

  private final TodoRepository todoRepository;

  @Autowired
  public TodoFacade(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public List<TodoTO> findAllTodos() {
    var mapper = TodoMapper.INSTANCE;
    return todoRepository.findAll().stream()
        .map(mapper::mapTodo)
        .collect(Collectors.toList());
  }

  @Override
  public TodoTO findTodo(Long todoId) {
    return TodoMapper.INSTANCE.mapTodo(todoRepository.findById(todoId).orElse(null));
  }
}
