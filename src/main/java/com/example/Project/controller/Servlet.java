package com.example.Project.controller;

import com.example.Project.Service.TaskService;
import com.example.Project.Service.TasksServiceImpl;
import com.example.Project.domain.SubTask;
import com.example.Project.domain.Task;
import com.example.Project.dto.SubTaskDTO;
import com.example.Project.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class Servlet {
    @Autowired
    private TaskService tasksService;

    @GetMapping("/tasks")
    public String index(Model model){
        List<String> errors = new ArrayList<>();
        if(tasksService.getTaskList() == null || tasksService.getTaskList().isEmpty()){
            errors = new ArrayList<>();
            errors.add("No tasks left to do");
        }
        if(!errors.isEmpty()){
            model.addAttribute("errors",errors);
            return "index";
        }
        model.addAttribute("tasks", tasksService.getTaskList());
        return "index";
    }
    @GetMapping("/tasks/{id}")
    public String detail(Model model, @PathVariable (name = "id") int id){
        TaskDTO detail = tasksService.getTask(id);
        Collection<SubTaskDTO> subTaskDTOCollection = tasksService.getAllSubtasksForTask(id);
        model.addAttribute("subtasks",subTaskDTOCollection);
        model.addAttribute("task",detail);
        return "detail";
    }
    @GetMapping("/tasks/new")
    public String showNewTask(Model model){
        model.addAttribute("task",new TaskDTO());
        return "form";
    }
    @PostMapping("/tasks/new")
    public String addTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        } else {
            tasksService.addTask(taskDTO);
            return "redirect:/tasks";
        }
    }
    @GetMapping("/tasks/edit/{id}")
    public String editTask(Model model, @PathVariable (name = "id") int id){
        model.addAttribute("task",new TaskDTO());
        model.addAttribute("id",id);
        return "edit";
    }

    @PostMapping("/tasks/edit/{id}")
    public String editTasks(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult, @PathVariable (name = "id") int id){
        if(bindingResult.hasErrors()){
            return "edit";
        }else {
            tasksService.updateTask(taskDTO,id);
            return "redirect:/tasks/{id}";
        }
    }

    @GetMapping("/tasks/{id}/sub/create")
    public String getFormSub(Model model, @PathVariable (name = "id") int id){
        model.addAttribute("subTask",new SubTaskDTO());
        model.addAttribute("id",id);
        return "formSubtask";
    }

    @PostMapping("/tasks/{id}/sub/create")
    public String createSubTask(@ModelAttribute @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult, @PathVariable (name = "id") int id){
        if(bindingResult.hasErrors()){
            return "formSubtask";
        }else {
            tasksService.addSubTask(subTaskDTO,id);
            return "redirect:/tasks/{id}";
        }
    }

    @GetMapping("/tasks/{subId}/sub")
    public String detailSubtask(Model model, @PathVariable (name = "subId") int subId){
        model.addAttribute("subtask", tasksService.getSubTask(subId));
        return "subTaskDetail";
    }

    @GetMapping("/tasks/sub/edit/{subId}")
    public String showEditSubForm(Model model, @PathVariable (name = "subId") int subId){
        model.addAttribute("subTask",new SubTaskDTO());
        model.addAttribute("subId",subId);
        return "editSub";
    }

    @PostMapping("/tasks/sub/edit/{subId}")
    public String editSubTask(@ModelAttribute @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult, @PathVariable (name = "subId") int subId){
        if(bindingResult.hasErrors()){
            return "editSub";
        }else {
            tasksService.updateSubtask(subTaskDTO,subId);
            return "redirect:/tasks/{subId}/sub";
        }
    }

    @PostMapping("/tasks/sub/delete/{subId}")
    public String deleteSubTask(@PathVariable (name = "subId") int subId){
        int parentId = tasksService.getSubTask(subId).getParentID();
        this.tasksService.removeSubTask(subId);
        return "redirect:/tasks/"+parentId;
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable (name = "id") int id, Model model){
        this.tasksService.removeTask(id);
        return index(model);
    }
}
