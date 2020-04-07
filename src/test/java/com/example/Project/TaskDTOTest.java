package com.example.Project;

import com.example.Project.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest
public class TaskDTOTest {
    private TaskDTO taskDTO = new TaskDTO();

    @Test
    public void testGetAndSetId(){
        //setup
        int id = 3;

        //method to test
        taskDTO.setId(id);
        int result = taskDTO.getId();

        //checks
        assertNotNull(result);
        assertEquals(id,result);
    }

    @Test
    public void testGetAndSetDescription(){
        //setup
        String description = "description";

        //method to test
        taskDTO.setDescription(description);
        String result = taskDTO.getDescription();

        //checks
        assertNotNull(result);
        assertEquals(description,result);
    }

    @Test
    public void testGetAndSetTitle(){
        //setup
        String title = "title";

        //method to test
        taskDTO.setTitle(title);
        String result = taskDTO.getTitle();

        //checks
        assertNotNull(result);
        assertEquals(title,result);
    }

    @Test
    public void testGetAndSetDuedate(){
        //setup
        LocalDateTime dueDate = LocalDateTime.of(2020,03,10,12,30);

        //method to test
        taskDTO.setDueDate(dueDate.toString());
        LocalDateTime result = taskDTO.getDueDate();

        //checks
        assertNotNull(result);
        assertEquals(dueDate,result);
    }
}
