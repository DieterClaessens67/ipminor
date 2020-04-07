package com.example.Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SubTask {
    private int parentID;
    @Id
    @GeneratedValue
    private int sId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private LocalDateTime dueDate;

    public SubTask() {
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        LocalDateTime duedate2 = LocalDateTime.parse(dueDate);
        this.dueDate = duedate2;
    }

    public int getParentID() {
        return this.parentID;
    }

    public void setParent(int parentID) {
        this.parentID = parentID;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
