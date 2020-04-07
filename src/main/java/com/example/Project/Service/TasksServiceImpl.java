package com.example.Project.Service;

import com.example.Project.domain.SubTask;
import com.example.Project.domain.Task;
import com.example.Project.dto.SubTaskDTO;
import com.example.Project.dto.TaskDTO;
import com.example.Project.repo.SubtaskRepo;
import com.example.Project.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.font.TextHitInfo;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Transactional
@Service
public class TasksServiceImpl implements TaskService{
    private final TaskRepo taskRepo;
    private final SubtaskRepo subtaskRepo;

    @Autowired
    public TasksServiceImpl(TaskRepo taskRepo, SubtaskRepo subtaskRepo) {
        this.taskRepo = taskRepo;
        this.subtaskRepo = subtaskRepo;
    }

    @Override
    public void addTask(TaskDTO taskDTO){
       Task task = new Task();
       task.setTitle(taskDTO.getTitle());
       task.setDueDate(taskDTO.getDueDate().toString());
       task.setDescription(taskDTO.getDescription());
       this.taskRepo.save(task);
    }

    @Override
    public List<TaskDTO> getTaskList() {
        return this.taskRepo.findAll().stream().map(h ->{
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setDescription(h.getDescription());
            taskDTO.setDueDate(h.getDueDate().toString());
            taskDTO.setTitle(h.getTitle());
            taskDTO.setId(h.getId());
            return taskDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(int id) {
        List<TaskDTO> taskDTOList = this.getTaskList(); //to test
        for(TaskDTO task:this.getTaskList()){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    @Override
    public void updateTask(TaskDTO taskNew, int id){
        Task task = this.taskRepo.getOne(id);
        task.setDescription(taskNew.getDescription());
        task.setDueDate(taskNew.getDueDate().toString());
        task.setTitle(taskNew.getTitle());
        this.taskRepo.save(task);
    }

    @Override
    public void addSubTask(SubTaskDTO subTaskDTO, int parentId) {
        SubTask subTask = new SubTask();
        subTask.setDescription(subTaskDTO.getDescription());
        subTask.setDueDate(subTaskDTO.getDueDate().toString());
        subTask.setParent(parentId);
        subTask.setTitle(subTaskDTO.getTitle());
        subTask.setsId(subTaskDTO.getsId());
        this.subtaskRepo.save(subTask);
    }

    @Override
    public SubTaskDTO getSubTask(int subId){
        SubTask subTask = this.subtaskRepo.getOne(subId);
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription(subTask.getDescription());
        subTaskDTO.setDueDate(subTask.getDueDate().toString());
        subTaskDTO.setParent(subTask.getParentID());
        subTaskDTO.setTitle(subTask.getTitle());
        subTaskDTO.setsId(subId);
        return subTaskDTO;
    }

    private List<SubTaskDTO> getAllSubtasks(){
        return this.subtaskRepo.findAll().stream().map(h -> {
            SubTaskDTO subTaskDTO = new SubTaskDTO();
            subTaskDTO.setTitle(h.getTitle());
            subTaskDTO.setDueDate(h.getDueDate().toString());
            subTaskDTO.setDescription(h.getDescription());
            subTaskDTO.setParent(h.getParentID());
            subTaskDTO.setsId(h.getsId());
            return subTaskDTO;
        }).collect(Collectors.toList());
    }

    private Map<Integer,List<SubTaskDTO>> getSubtasksPerTask(){
        Map<Integer,List<SubTaskDTO>> listMap = new HashMap<>();
        for (TaskDTO taskDTO:getTaskList()){
            listMap.put(taskDTO.getId(),new ArrayList<>());
        }
        for(Integer key:listMap.keySet()) {
            for (SubTaskDTO subTaskDTO : getAllSubtasks()) {
                if(subTaskDTO.getParentID()==key){
                    listMap.get(key).add(subTaskDTO);
                }
            }
        }
        return listMap;
    }

    @Override
    public List<SubTaskDTO> getAllSubtasksForTask(int id){
        Map<Integer,List<SubTaskDTO>> listMap = getSubtasksPerTask();
        return listMap.get(id);
    }

    @Override
    public void updateSubtask(SubTaskDTO subTaskDTONew, int subId){
        SubTask subTask = this.subtaskRepo.getOne(subId);
        subTask.setTitle(subTaskDTONew.getTitle());
        subTask.setDueDate(subTaskDTONew.getDueDate().toString());
        subTask.setDescription(subTaskDTONew.getDescription());
        subTask.setsId(subId);
        this.subtaskRepo.save(subTask);
    }

    @Override
    public void removeSubTask(int subId){
        this.subtaskRepo.deleteById(subId);
    }

    @Override
    public void removeTask(int id) {
        removeTaskChildren(id);
        this.taskRepo.deleteById(id);
    }

    private void removeTaskChildren(int id){
        for(SubTaskDTO subTaskDTO: getAllSubtasksForTask(id)){
            this.removeSubTask(subTaskDTO.getsId());
        }
    }

    @Override
    public void clear(){
       for(TaskDTO taskDTO:getTaskList()){
           removeTaskChildren(taskDTO.getId());
           removeTask(taskDTO.getId());
       }
    }
}
