package com.example.Project.dto;

import com.example.Project.domain.SubTask;
import com.example.Project.domain.Task;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private int id;
    @NotEmpty
    private String title;
    private LocalDateTime dueDate;
    @NotEmpty
    private String description;

    public TaskDTO() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        LocalDateTime localDate = LocalDateTime.parse(dueDate);
        this.dueDate = localDate;
    }



    @Override
    public String toString(){
        String result = this.getTitle()+": due "+this.getDueDate().getMonth()+" "+this.getDueDate().getDayOfMonth()
                +" "+this.getDueDate().getYear()+" at "+this.getDueDate().getHour();
        if(this.dueDate.getHour()<12){
            result += " am";
        } else {
            result+= " pm";
        }
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof TaskDTO){
            TaskDTO t = (TaskDTO) o;
            if(t.getTitle().equalsIgnoreCase(this.getTitle()) && t.getDescription().equalsIgnoreCase(this.getDescription())
             && this.dueDate.getHour() == t.getDueDate().getHour() && t.dueDate.getDayOfMonth() == this.dueDate.getDayOfMonth()
            && t.dueDate.getMonth() == this.dueDate.getMonth() && t.dueDate.getYear() == this.dueDate.getYear()){
                return true;
            }
        }
        return false;
    }
}
