package br.com.theo.projetolistadetarefas.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.theo.projetolistadetarefas.model.TaskModel;
import br.com.theo.projetolistadetarefas.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<TaskModel> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public TaskModel createTask(TaskModel task) {
        return taskRepository.save(task);
    }

    public TaskModel updateTask(int id, TaskModel updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setDueDate(updatedTask.getDueDate());
                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
    
    private boolean isValidStatus(String status) {
        return status != null && Arrays.asList("PENDENTE", "EM_ANDAMENTO", "CONCLUIDA").contains(status);
    }
}

