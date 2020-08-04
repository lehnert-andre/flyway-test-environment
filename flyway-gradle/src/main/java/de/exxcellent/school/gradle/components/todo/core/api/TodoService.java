package de.exxcellent.school.gradle.components.todo.core.api;

import de.exxcellent.school.gradle.components.todo.core.api.to.TodoTO;

import java.util.List;

public interface TodoService {

  List<TodoTO> findAllTodos();

  TodoTO findTodo(Long todoId);
}
