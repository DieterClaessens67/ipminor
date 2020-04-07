package com.example.Project.dto;

import com.example.Project.domain.SubTask;
import com.example.Project.domain.Task;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class SubTaskDTO {
    private int sId;
    private int parentID;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private LocalDateTime dueDate;

    public SubTaskDTO() {
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
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

    public List<SubTask> getSubTaskList(){
        return null;
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
        if(o instanceof SubTaskDTO){
            SubTaskDTO t = (SubTaskDTO) o;
            if(t.getTitle().equalsIgnoreCase(this.getTitle()) && t.getDescription().equalsIgnoreCase(this.getDescription())
                    && this.dueDate.getHour() == t.getDueDate().getHour() && t.dueDate.getDayOfMonth() == this.dueDate.getDayOfMonth()
                    && t.dueDate.getMonth() == this.dueDate.getMonth() && t.dueDate.getYear() == this.dueDate.getYear()){
                return true;
            }
        }
        return false;
    }
}
