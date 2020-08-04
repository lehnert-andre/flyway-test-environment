package de.exxcellent.school.gradle.components.todo.rest;

import de.exxcellent.school.gradle.components.todo.core.api.TodoService;
import de.exxcellent.school.gradle.components.todo.core.api.to.TodoTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/init-todos.sql"})
class TodoControllerTest {

  private static final List<TodoTO> EXPECTED_TODOS = List.of(
      new TodoTO(1L, "Hallo Welt", OffsetDateTime.now()),
      new TodoTO(2L, "Flyway lernen", OffsetDateTime.now()),
      new TodoTO(3L, "Datenbankmigration von A bis Z", OffsetDateTime.now())
  );

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Mock
  private TodoService todoService;

  @InjectMocks
  TodoController todoController;

  @BeforeEach
  void setUp() {
    Mockito.when(todoService.findAllTodos()).thenReturn(EXPECTED_TODOS);
    EXPECTED_TODOS.forEach(todo -> {
      Mockito.when(todoService.findTodo(todo.getId())).thenReturn(todo);
    });
  }

  @Test
  void findAll() {
    var actual = todoController.findAll();

    assertFalse(actual.isEmpty(), "Todo list not empty");

    for (int i = 0; i < actual.size(); i++) {
      TodoTO expectedTodo = EXPECTED_TODOS.get(i);
      TodoTO actualTodo = actual.get(i);

      assertEquals(expectedTodo.getId(), actualTodo.getId());
      assertEquals(expectedTodo.getTask(), actualTodo.getTask());
      assertEquals(expectedTodo.getCreatedAt(), actualTodo.getCreatedAt());
    }
  }

  @Test
  void findById() {
    var expected = EXPECTED_TODOS.get(1);

    var actual = todoController.findById(expected.getId());

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getTask(), actual.getTask());
    assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
  }

  @Test
  @Sql(scripts = {"/init-todos.sql"})
  void findAll_viaREST() throws Exception {
    ResponseEntity<TodoTO[]> response = restTemplate.getForEntity(new URL(getFindAllUrl()).toString(), TodoTO[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    var actual = response.getBody();
    assertNotNull(actual, "Todo list exists");

    assertTrue(actual.length > 0, "Todo list not empty");
    assertTrue( actual.length >= 3, "Todo list has at least 3 entries");

    for (int i = 0; i < 3; i++) {
      TodoTO expectedTodo = EXPECTED_TODOS.get(i);
      TodoTO actualTodo = actual[i];

      assertEquals(expectedTodo.getId(), actualTodo.getId());
      assertEquals(expectedTodo.getTask(), actualTodo.getTask());
    }
  }

  @Test
  void findById_viaREST() throws Exception {
    var expected = EXPECTED_TODOS.get(1);

    ResponseEntity<TodoTO> response = restTemplate.getForEntity(new URL(getFindByIdUrl(expected.getId())).toString(), TodoTO.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    var actual = response.getBody();
    assertNotNull(actual, "Todo list exists");

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getTask(), actual.getTask());
  }

  private String getFindAllUrl() {
    return "http://localhost:" + port + "/api/todos";
  }

  private String getFindByIdUrl(long id) {
    return "http://localhost:" + port + "/api/todos/" + id;
  }
}