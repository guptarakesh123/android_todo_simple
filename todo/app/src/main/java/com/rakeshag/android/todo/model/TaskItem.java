package com.rakeshag.android.todo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class TaskItem {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="details")
    private String details;

    @ColumnInfo(name="start_date")
    private String startDate;

    @ColumnInfo(name="end_date")
    private String endDate;

    @ColumnInfo(name="priority")
    private TaskPriority priority;

    @Ignore
    public TaskItem() {
        this("", "","","", TaskPriority.Unset);
    }

    public TaskItem(String name, String details, String startDate, String endDate, TaskPriority priority) {
        this.name = name;
        this.details = details;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setPriority(String priority) {
        this.priority = TaskPriority.getPriority(priority);
    }
}
