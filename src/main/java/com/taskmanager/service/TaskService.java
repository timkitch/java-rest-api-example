package com.taskmanager.service;

import com.taskmanager.model.Task;
import java.util.List;
import java.util.Set;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    Task addDependency(Long taskId, Long dependencyId);
    Task removeDependency(Long taskId, Long dependencyId);
    Set<Task> getDependencies(Long taskId);
    boolean canCompleteTask(Long taskId);
}
