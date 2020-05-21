package com.example.Project.controller;

import com.example.Project.Service.TaskService;
import com.example.Project.domain.Task;
import com.example.Project.dto.SubTaskDTO;
import com.example.Project.dto.TaskDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final TaskService taskService;

    public RestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks() {
        return taskService.getTaskList();
    }

    @GetMapping("/task/{id}")
    @ResponseBody
    public TaskDTO getTask(@PathVariable (name = "id") int id) {
        return taskService.getTask(id);
    }

    @PostMapping("/task")
    public TaskDTO createNewTask(@RequestBody @Valid TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
        return taskDTO;
    }

    @PostMapping("/task/edit/{id}")
    public TaskDTO editTask(@RequestBody @Valid TaskDTO taskDTO, @PathVariable (name = "id") int id) {
        taskService.updateTask(taskDTO,id);
        return taskDTO;
    }

    @PostMapping("task/{id}/sub/create")
    public SubTaskDTO createSubTask(@RequestBody @Valid SubTaskDTO subTaskDTO, @PathVariable (name = "id") int id){
        taskService.addSubTask(subTaskDTO,id);
        return subTaskDTO;
    }

    @GetMapping("/sub/{subId}")
    @ResponseBody
    public SubTaskDTO getSubTask(@PathVariable (name = "subId") int subId) {
        return taskService.getSubTask(subId);
    }

    @PostMapping("/sub/edit/{subId}")
    public SubTaskDTO editSubTask(@PathVariable (name = "subId") int subId, @RequestBody @Valid SubTaskDTO subTaskDTO){
        taskService.updateSubtask(subTaskDTO,subId);
        return subTaskDTO;
    }

    @PostMapping("/sub/delete/{subId}")
    public SubTaskDTO deleteSubTask(@PathVariable (name = "subId") int subId){
        SubTaskDTO result = taskService.getSubTask(subId);
        taskService.removeSubTask(subId);
        return result;
    }

    @PostMapping("/task/delete/{id}")
    public TaskDTO deleteTask(@PathVariable (name = "id") int id){
        TaskDTO result = taskService.getTask(id);
        taskService.removeTask(id);
        return result;
    }

    @GetMapping("task/{id}/sub")
    @ResponseBody
    public List<SubTaskDTO> getSubTasksForTask(@PathVariable (name = "id") int id){
        return taskService.getAllSubtasksForTask(id);
    }
}
