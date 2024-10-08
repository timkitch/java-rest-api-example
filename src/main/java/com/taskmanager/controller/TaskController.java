package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Retrieves all tasks.
     *
     * This endpoint handles GET requests to retrieve all tasks. It uses the taskService
     * to fetch all tasks from the system and returns them as a list.
     *
     * @return ResponseEntity<List<Task>> A ResponseEntity containing a List of all Task objects
     *         and a 200 OK status code.
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retrieves a task by its ID.
     *
     * This endpoint handles GET requests to retrieve a specific task. It takes the task ID
     * as a path variable, fetches the corresponding task using the taskService,
     * and returns it.
     *
     * @param id The ID of the task to be retrieved, provided as a path variable.
     * @return ResponseEntity<Task> A ResponseEntity containing the requested Task object
     *         and a 200 OK status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Creates a new task.
     *
     * This endpoint handles POST requests to create a new task. It takes a Task object
     * in the request body, creates the task using the taskService, and returns the
     * created task with a 201 CREATED status.
     *
     * @param task The Task object to be created, provided in the request body.
     * @return ResponseEntity<Task> A ResponseEntity containing the created Task object
     *         and a 201 CREATED status code.
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * Updates an existing task.
     *
     * This endpoint handles PUT requests to update an existing task. It takes the task ID
     * as a path variable and a Task object in the request body. The task with the given ID
     * is updated using the taskService, and the updated task is returned.
     *
     * @param id The ID of the task to be updated, provided as a path variable.
     * @param task The Task object with updated information, provided in the request body.
     * @return ResponseEntity<Task> A ResponseEntity containing the updated Task object
     *         and a 200 OK status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     *
     * This endpoint handles DELETE requests to remove a task. It takes the task ID
     * as a path variable, deletes the corresponding task using the taskService,
     * and returns a response with no content, indicating successful deletion.
     *
     * @param id The ID of the task to be deleted, provided as a path variable.
     * @return ResponseEntity<Void> A ResponseEntity with a 204 NO_CONTENT status code,
     *         indicating that the task was successfully deleted and there's no content to return.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds a dependency to a task.
     *
     * This endpoint handles POST requests to add a dependency to a task. It takes the task ID
     * and the dependency ID as path variables, adds the dependency using the taskService,
     * and returns the updated task.
     *
     * @param id The ID of the task to which the dependency will be added.
     * @param dependencyId The ID of the task to be added as a dependency.
     * @return ResponseEntity<Task> A ResponseEntity containing the updated Task object
     *         and a 200 OK status code.
     */
    @PostMapping("/{id}/dependencies/{dependencyId}")
    public ResponseEntity<Task> addDependency(@PathVariable Long id, @PathVariable Long dependencyId) {
        Task updatedTask = taskService.addDependency(id, dependencyId);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Removes a dependency from a task.
     *
     * This endpoint handles DELETE requests to remove a dependency from a task. It takes the task ID
     * and the dependency ID as path variables, removes the dependency using the taskService,
     * and returns the updated task.
     *
     * @param id The ID of the task from which the dependency will be removed.
     * @param dependencyId The ID of the task to be removed as a dependency.
     * @return ResponseEntity<Task> A ResponseEntity containing the updated Task object
     *         and a 200 OK status code.
     */
    @DeleteMapping("/{id}/dependencies/{dependencyId}")
    public ResponseEntity<Task> removeDependency(@PathVariable Long id, @PathVariable Long dependencyId) {
        Task updatedTask = taskService.removeDependency(id, dependencyId);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Retrieves all dependencies of a task.
     *
     * This endpoint handles GET requests to retrieve all dependencies of a task. It takes the task ID
     * as a path variable, retrieves the dependencies using the taskService, and returns the set of
     * dependent tasks.
     *
     * @param id The ID of the task for which to retrieve dependencies.
     * @return ResponseEntity<Set<Task>> A ResponseEntity containing a Set of Task objects
     *         representing the dependencies, and a 200 OK status code.
     */
    @GetMapping("/{id}/dependencies")
    public ResponseEntity<Set<Task>> getDependencies(@PathVariable Long id) {
        Set<Task> dependencies = taskService.getDependencies(id);
        return ResponseEntity.ok(dependencies);
    }
}
