package de.exxcellent.school.gradle.components.todo.core.api.to;

import java.time.OffsetDateTime;

public class TodoTO {

  private Long id;

  private String task;
  private OffsetDateTime createdAt;

  public TodoTO() {
  }

  public TodoTO(Long id, String task, OffsetDateTime createdAt) {
    this.id = id;
    this.task = task;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
