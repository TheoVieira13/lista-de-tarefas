package br.com.theo.projetolistadetarefas.repository;

import br.com.theo.projetolistadetarefas.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.TaskExecutionOutcome;

public interface TaskRepository extends JpaRepository<TaskModel, Integer> {

}
