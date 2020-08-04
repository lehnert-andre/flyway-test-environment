package de.exxcellent.school.gradle.components.todo.core.impl.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
