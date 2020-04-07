package com.example.Project;

import com.example.Project.domain.SubTask;
import com.example.Project.dto.SubTaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert.*;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest
public class SubTaskDTOTest {
    private SubTaskDTO subTask = new SubTaskDTO();
    @org.junit.jupiter.api.Test
    public void testSetAndGetDueDate(){
        //setup
        LocalDateTime dueDate = LocalDateTime.of(2020,05,30,12,30);

        //method to be tested
        subTask.setDueDate(dueDate.toString());
        LocalDateTime result = subTask.getDueDate();

        // checks
        assertNotNull(result);
        assertEquals(dueDate,result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAndSetParentId(){
        //setup
        int parentId = 1;

        //method to be tested
        subTask.setParent(parentId);
        int result = subTask.getParentID();

        // checks
        assertNotNull(result);
        assertEquals(parentId,result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAndSetId(){
        //setup
        int id = 1;

        //method to be tested
        subTask.setsId(id);
        int result = subTask.getsId();

        // checks
        assertNotNull(result);
        assertEquals(id,result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAndSetTitle(){
        //setup
        String title = "title";

        //method to be tested
        subTask.setTitle(title);
        String result = subTask.getTitle();

        //checks
        assertNotNull(result);
        assertEquals(title,result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAndSetDescription(){
        //setup
        String description = "description";

        //method to be tested
        subTask.setDescription(description);
        String result = subTask.getDescription();

        //checks
        assertNotNull(result);
        assertEquals(description,result);
    }

    @Test
    public void testToString(){
        //setup
        subTask.setTitle("title");
        subTask.setDueDate(LocalDateTime.of(2020,05,30,12,30).toString());

        //method to be tested
        String result = subTask.toString();

        //checks
        assertNotNull(result);
        assertEquals("title: due MAY 30 2020 at 12 pm",result);
    }
}
