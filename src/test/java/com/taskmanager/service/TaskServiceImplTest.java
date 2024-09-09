package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateTask_shouldUpdateTaskFields() {
        // Arrange
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Old Title");
        existingTask.setDescription("Old Description");
        existingTask.setCompleted(false);

        Task updatedTaskDetails = new Task();
        updatedTaskDetails.setId(taskId);
        updatedTaskDetails.setTitle("New Title");
        updatedTaskDetails.setDescription("New Description");
        updatedTaskDetails.setCompleted(true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task result = taskService.updateTask(taskId, updatedTaskDetails);

        // Assert
        assertNotNull(result);
        assertEquals(updatedTaskDetails.getTitle(), result.getTitle());
        assertEquals(updatedTaskDetails.getDescription(), result.getDescription());
        assertEquals(updatedTaskDetails.isCompleted(), result.isCompleted());

        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void updateTask_shouldThrowExceptionWhenTaskNotFound() {
        // Arrange
        Long taskId = 1L;
        Task updatedTaskDetails = new Task();
        updatedTaskDetails.setId(taskId);
        updatedTaskDetails.setTitle("New Title");
        updatedTaskDetails.setDescription("New Description");
        updatedTaskDetails.setCompleted(true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> taskService.updateTask(taskId, updatedTaskDetails));

        verify(taskRepository).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
