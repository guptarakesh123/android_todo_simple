package com.rakeshag.android.todo.presenter;

import android.content.Context;

import com.rakeshag.android.todo.model.TaskItem;
import com.rakeshag.android.todo.model.TaskPriority;
import com.rakeshag.android.todo.repository.RepositoryProvider;
import com.rakeshag.android.todo.repository.TasksRepo;

import androidx.lifecycle.ViewModel;

public class AddTaskActivityViewModel extends ViewModel {

    private TaskItem mTaskItem;
    private TasksRepo mTasksRepo;

    public AddTaskActivityViewModel(Context applicationContext) {
        mTaskItem = new TaskItem();
        mTasksRepo = RepositoryProvider.getInstance(applicationContext).getTasksRepo();
    }

    public String getName() {
        return mTaskItem.getName();
    }

    public void setName(String name) {
        mTaskItem.setName(name);
    }

    public String getDetails() {
        return mTaskItem.getDetails();
    }

    public void setDetails(String details) {
        mTaskItem.setDetails(details);
    }

    public String getStartDate() {
        return mTaskItem.getStartDate();
    }

    public void setStartDate(String startDate) {
        mTaskItem.setStartDate(startDate);
    }

    public String getEndDate() {
        return mTaskItem.getEndDate();
    }

    public void setEndDate(String endDate) {
        mTaskItem.setEndDate(endDate);
    }

    public TaskPriority getPriority() {
        return mTaskItem.getPriority();
    }

    public void setPriority(String priority) {
        mTaskItem.setPriority(priority);
    }


    public void reset() {
        mTaskItem = new TaskItem();
    }

    public void commitData() {
        mTasksRepo.addTaskItem(mTaskItem);
    }
}
