package de.exxcellent.school.gradle.components.todo.core.impl.business;

import de.exxcellent.school.gradle.components.todo.core.api.to.TodoTO;
import de.exxcellent.school.gradle.components.todo.core.impl.data.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {

  TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

  TodoTO mapTodo(Todo todo);

  Todo mapTodo(TodoTO todoTO);
}
