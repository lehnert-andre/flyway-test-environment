package de.exxcellent.school.flyway.components.todo.core.impl.business;

import de.exxcellent.school.flyway.components.todo.core.api.to.TodoTO;
import de.exxcellent.school.flyway.components.todo.core.impl.data.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {

  TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

  TodoTO mapTodo(Todo todo);

  Todo mapTodo(TodoTO todoTO);
}
