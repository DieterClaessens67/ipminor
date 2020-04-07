package com.example.Project;

import com.example.Project.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
public class TaskTest {
    private Task task = new Task();

    @Test
    public void testGetAndSetId(){
        //setup
        int id = 3;

        //method to test
        task.setId(id);
        int result = task.getId();

        //checks
        assertNotNull(result);
        assertEquals(id,result);
    }

    @Test
    public void testGetAndSetDescription(){
        //setup
        String description = "description";

        //method to test
        task.setDescription(description);
        String result = task.getDescription();

        //checks
        assertNotNull(result);
        assertEquals(description,result);
    }

    @Test
    public void testGetAndSetTitle(){
        //setup
        String title = "title";

        //method to test
        task.setTitle(title);
        String result = task.getTitle();

        //checks
        assertNotNull(result);
        assertEquals(title,result);
    }

    @Test
    public void testGetAndSetDuedate(){
        //setup
        LocalDateTime dueDate = LocalDateTime.of(2020,03,10,12,30);

        //method to test
        task.setDueDate(dueDate.toString());
        LocalDateTime result = task.getDueDate();

        //checks
        assertNotNull(result);
        assertEquals(dueDate,result);
    }
}
