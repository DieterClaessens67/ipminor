package com.example.Project;

import com.example.Project.Service.TaskService;
import com.example.Project.domain.SubTask;
import com.example.Project.domain.Task;
import com.example.Project.dto.SubTaskDTO;
import com.example.Project.dto.TaskDTO;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void testAddTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,10,12,30).toString());

        //method to test
        taskService.addTask(taskDTO);
        List<TaskDTO> list = taskService.getTaskList();

        //checks
        assertNotNull(list.get(0));
        assertEquals(taskDTO,list.get(0));
    }

    @Test
    public void testGetTaskList(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskDTO.setId(1);

        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setTitle("title2");
        taskDTO2.setDescription("description2");
        taskDTO2.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskDTO2.setId(2);

        taskService.addTask(taskDTO);
        taskService.addTask(taskDTO2);
        //method to test
        List<TaskDTO> result = taskService.getTaskList();

        //checks
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0));
        assertEquals(taskDTO,result.get(0));
        assertNotNull(result.get(1));
        assertEquals(taskDTO2,result.get(1));
    }

    @Test
    public void testUpdateTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskDTO.setDescription("description");
        taskDTO.setTitle("title");
        taskDTO.setId(1);
        taskService.addTask(taskDTO);

        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setId(1);
        taskDTO1.setTitle("titleUpdate");
        taskDTO1.setDescription("descriptionUpdate");
        taskDTO1.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());

        //method to test
        taskService.updateTask(taskDTO1,taskService.getTaskList().get(0).getId());
       List<TaskDTO> taskDTOList = taskService.getTaskList();

        //checks
        assertEquals(taskDTO1,taskDTOList.get(0));
    }

    @Test
    public void testAddSubTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1);
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription("descsub");
        subTaskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO.setTitle("titleSub");

        //method to test
        taskService.addSubTask(subTaskDTO,taskService.getTaskList().get(0).getId());
        List<TaskDTO> taskDTOList = taskService.getTaskList();
        List<SubTaskDTO> subTaskDTOList = taskService.getAllSubtasksForTask(taskDTOList.get(0).getId());

        //checks
        assertNotNull(subTaskDTOList.get(0));
        assertEquals(subTaskDTO,subTaskDTOList.get(0));
    }

    @Test
    public void testGetTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1);
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskService.addTask(taskDTO);

        //method to test
        TaskDTO result = taskService.getTask(taskService.getTaskList().get(0).getId());

        //checks
        assertNotNull(result);
        assertEquals(taskDTO,result);
    }

    @Test
    public void testGetSubTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1);
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription("descsub");
        subTaskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO.setTitle("titleSub");
        taskService.addSubTask(subTaskDTO,taskService.getTaskList().get(0).getId());

        //method to test
        SubTaskDTO result = taskService.getSubTask(taskService.getAllSubtasksForTask(taskService.getTaskList().get(0).getId()).get(0).getsId());

        //checks
        assertNotNull(result);
        assertEquals(subTaskDTO,result);
    }

    @Test
    public void testGetAllSubtasksForTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1);
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription("descsub");
        subTaskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO.setTitle("titleSub");
        taskService.addSubTask(subTaskDTO,taskService.getTaskList().get(0).getId());

        SubTaskDTO subTaskDTO1 = new SubTaskDTO();
        subTaskDTO1.setDescription("descsub1");
        subTaskDTO1.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO1.setTitle("titleSub1");
        taskService.addSubTask(subTaskDTO1,taskService.getTaskList().get(0).getId());

        //method to test
        List<SubTaskDTO> result = taskService.getAllSubtasksForTask(taskService.getTaskList().get(0).getId());

        //checks
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2,result.size());
        assertEquals(subTaskDTO,result.get(0));
        assertEquals(subTaskDTO1,result.get(1));
    }

    @Test
    public void testUpdateSubTask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1);
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription("descsub");
        subTaskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO.setTitle("titleSub");
        taskService.addSubTask(subTaskDTO,taskService.getTaskList().get(0).getId());

        SubTaskDTO subTaskDTO1 = new SubTaskDTO();
        subTaskDTO1.setDescription("descsub1");
        subTaskDTO1.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO1.setTitle("titleSub1");

        //method to test
        taskService.updateSubtask(subTaskDTO1,taskService.getAllSubtasksForTask(taskService.getTaskList().get(0).getId()).get(0).getsId());
        SubTaskDTO result = taskService.getSubTask(taskService.getAllSubtasksForTask(taskService.getTaskList().get(0).getId()).get(0).getsId());

        //checks
        assertNotNull(result);
        assertEquals(subTaskDTO1,result);
    }

    @Test
    public void testRemoveSubtask(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1);
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription("descsub");
        subTaskDTO.setDueDate(LocalDateTime.of(2020,03,2,12,30).toString());
        subTaskDTO.setTitle("titleSub");
        taskService.addSubTask(subTaskDTO,taskService.getTaskList().get(0).getId());

        //method to test
        taskService.removeSubTask(taskService.getAllSubtasksForTask(taskService.getTaskList().get(0).getId()).get(0).getsId());

        //checks
        assertTrue(taskService.getAllSubtasksForTask(taskService.getTaskList().get(0).getId()).isEmpty());
    }

    @AfterEach
    public void clear(){
        taskService.clear();

    }
}
