package com.rakeshag.android.todo.repository;

import android.content.Context;

public class RepositoryProvider {
    private static RepositoryProvider instance;
    private final TasksRepo mTasksRepo;

    private RepositoryProvider(TasksRepo tasksRepo) {
        mTasksRepo = tasksRepo;
    }

    public static RepositoryProvider getInstance(Context applicationContext) {
        if (instance == null) {
            instance = new RepositoryProvider(new TasksRepo(applicationContext));
        }
        return instance;
    }

    public TasksRepo getTasksRepo() {
        return mTasksRepo;
    }
}
