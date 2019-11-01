package com.rakeshag.android.todo.presenter;

import android.content.Context;

import com.rakeshag.android.todo.model.TaskItem;
import com.rakeshag.android.todo.repository.RepositoryProvider;
import com.rakeshag.android.todo.repository.TasksRepo;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private TasksRepo mTasksRepo;

    public MainActivityViewModel(Context applicationContext) {
        mTasksRepo = RepositoryProvider.getInstance(applicationContext).getTasksRepo();
    }

    public TaskItem getTask(int position) {
        return mTasksRepo.get(position);
    }

    public int getDataSize() {
        return mTasksRepo.size();
    }

    public void removeTask(int position) {
        mTasksRepo.remove(position);
        //changeFlag.setValue(!changeFlag.getValue());
    }
}
