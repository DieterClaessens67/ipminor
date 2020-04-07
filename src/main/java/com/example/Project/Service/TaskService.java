package com.example.Project.Service;

import com.example.Project.domain.Task;
import com.example.Project.dto.SubTaskDTO;
import com.example.Project.dto.TaskDTO;

import java.util.Collection;
import java.util.List;

public interface TaskService {
    void addTask(TaskDTO taskDTO);

    List<TaskDTO> getTaskList();

    TaskDTO getTask(int id);

    void updateTask(TaskDTO taskNew, int id);

    void addSubTask(SubTaskDTO subTaskDTO, int id);

    SubTaskDTO getSubTask(int subId);

    List<SubTaskDTO> getAllSubtasksForTask(int id);

    void updateSubtask(SubTaskDTO subTaskDTONew, int subId);

    void removeSubTask(int subId);

    void removeTask(int id);

    void clear();
}
