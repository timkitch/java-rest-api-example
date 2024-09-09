package com.taskmanager.service;

import com.taskmanager.exception.TaskNotFoundException;
import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Override
    public Task addDependency(Long taskId, Long dependencyId) {
        Task task = getTaskById(taskId);
        Task dependency = getTaskById(dependencyId);
        task.getDependencies().add(dependency);
        return taskRepository.save(task);
    }

    @Override
    public Task removeDependency(Long taskId, Long dependencyId) {
        Task task = getTaskById(taskId);
        Task dependency = getTaskById(dependencyId);
        task.getDependencies().remove(dependency);
        return taskRepository.save(task);
    }

    @Override
    public Set<Task> getDependencies(Long taskId) {
        Task task = getTaskById(taskId);
        return task.getDependencies();
    }

    @Override
    public boolean canCompleteTask(Long taskId) {
        Task task = getTaskById(taskId);
        for (Task dependency : task.getDependencies()) {
            if (!dependency.isCompleted()) {
                return false;
            }
        }
        return true;
    }
}
