package com.example.Project.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty
    private String title;
    private LocalDateTime dueDate;
    @NotEmpty
    private String description;

    public Task() {}
    public int getId() {
        return this.id;
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
}
